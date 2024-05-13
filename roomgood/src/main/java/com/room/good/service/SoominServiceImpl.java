package com.room.good.service;

import com.room.good.dto.*;
import com.room.good.entity.ClubMember;
import com.room.good.entity.ContactProduct;
import com.room.good.entity.Product;
import com.room.good.entity.Review;
import com.room.good.repository.ProductRepository;
import com.room.good.repository.ReviewRepository;
import com.room.good.repository.TimeSaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class SoominServiceImpl implements SoominService{

    private final TimeSaleRepository timeSaleRepository;
    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public List<ProductListDTO> getProductList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("pno").descending());

        Page<Product> result = productRepository.findAll(pageable);

        List<Product> pageProduct = result.getContent();

        return pageProduct.stream().map(paging -> ProductListDTO.builder().pname(paging.getPname()).price(paging.getPrice()).pno(paging.getPno()).build()).collect(Collectors.toList());
    }

    @Override
    public Long getCountAll() {

        return productRepository.countByAll();
    }

    @Override
    public List<TimeSaleDTO> getTimeSaleList() {

        List<TimeSaleDTO> timeSaleDTOList = timeSaleRepository.findAll().stream().map(timeSale ->  TimeSaleDTO.builder()
                .endTime(timeSale.getEndTime())
                .tno(timeSale.getTno())
                .img(timeSale.getImg())
                .url(timeSale.getUrl())
                .sale(timeSale.getSale())
                .originalPrice(timeSale.getOriginalPrice())
                .price((long) (timeSale.getOriginalPrice()-Math.ceil((double) timeSale.getOriginalPrice()/100)*timeSale.getSale()))
                .content(timeSale.getContent())
                .endTime(timeSale.getEndTime())
                .build()).collect(Collectors.toList());


        return timeSaleDTOList;
    }

    @Override
    public List<ReviewDTO> getReviewListAll(Long pno, PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("rnum").descending());

        return reviewRepository.findByProduct(Product.builder().pno(pno).build(),pageable).stream().map(review ->
                ReviewDTO.builder()
                        .rnum(review.getRnum())
                        .grade(review.getGrade())
                        .mid(review.getClubMember().getId())
                        .pno(review.getProduct().getPno())
                        .nickName(review.getClubMember().getNickname())
                        .text(review.getText())
                        .regDate(review.getRegDate())
                        .modDate(review.getModDate())
                        .build()
                ).collect(Collectors.toList());
    }

    @Override
    public void reviewRegister(ReviewDTO reviewDTO) {
        Review review = Review.builder()
                .rnum(reviewDTO.getRnum())
                .clubMember(ClubMember.builder().id(reviewDTO.getMid()).build())
                .grade(reviewDTO.getGrade())
                .text(reviewDTO.getText())
                .product(Product.builder().pno(reviewDTO.getPno()).build())
                .build();
        reviewRepository.save(review);
    }

    @Override
    public void reviewModify(ReviewDTO reviewDTO) {
        Optional<Review> result = reviewRepository.findById(reviewDTO.getRnum());

        if(result.isPresent()){
            Review review = result.get();

            review.setText(reviewDTO.getText());
            review.setGrade(reviewDTO.getGrade());

            reviewRepository.save(review);
        }
    }

    @Override
    public void reviewDelete(Long rnum) {
        reviewRepository.deleteById(rnum);
    }

    @Override
    public Long getCountOfProduct(Long pno) {
        Product product = Product.builder().pno(pno).build();

        return reviewRepository.countByProduct(product);
    }


}
