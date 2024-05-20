package com.room.good.service;

import com.room.good.dto.WishListDTO;
import com.room.good.entity.ClubMember;
import com.room.good.entity.Product;
import com.room.good.entity.WishList;
import com.room.good.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService{

    private final ClubMemberRepository clubMemberRepository;
    private final ProductRepository productRepository;
    private final WishRepository wishRepository;

    @Override
    @Transactional
    public boolean addwish(String email, Long pno) {

        Optional<ClubMember> byEmail2 = clubMemberRepository.findByEmail2(email);
        Optional<Product> byId = productRepository.findById(pno);

        ClubMember clubMember = byEmail2.get();
        Product product = byId.get();
        WishList wishList =new WishList();
        wishList.setClubMember(clubMember);
        wishList.setProduct(product);
        wishList.setStatus(true);


        log.info(wishList+"wishListwishList");
        wishRepository.save(wishList);
        return true;
    }

    @Override
    @Transactional
    public WishListDTO getlist(Long id) {
        Optional<ClubMember> byId = clubMemberRepository.findById(id);
        List<WishList> byClubMemberId = wishRepository.findByClubMemberId(id);

        WishListDTO wishListDTO = new WishListDTO();
        wishListDTO.setWishlist(byClubMemberId);
        log.info(byClubMemberId+"byClubMemberIdbyClubMemberId");
        log.info(byClubMemberId.get(0).getProduct().getPname()+"getPname");
        log.info(byClubMemberId.get(0).getProduct().getPrice()+"getPrice");
        log.info(byClubMemberId.get(0).getProduct().getContent()+"getContent");

        return wishListDTO;
    }

    @Override
    @Transactional
    public boolean delete(Long wno) {
        log.info("wnownownownowno"+wno);
        wishRepository.deleteAllByProductPno(wno);
        log.info("aaaaaaaaaaaaaaaaaaaaaaa");
        return true;
    }

    @Override
    public boolean findwish(Long id) {
        List<WishList> byClubMemberId = wishRepository.findByClubMemberId(id);

        if(byClubMemberId.isEmpty()){

            return false;
        }else {

            return true;
        }


    }

    ;
}
