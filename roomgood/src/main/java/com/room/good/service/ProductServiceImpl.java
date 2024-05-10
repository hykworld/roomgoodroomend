package com.room.good.service;



import com.room.good.entity.ProductImage2;
import com.room.good.repository.CategoryRepository;

import com.room.good.repository.ProductImageRepository2;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.log4j.Log4j2;

import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.PageResultDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.room.good.dto.ProductDTO;
import com.room.good.dto.ProductImageDTO;

import com.room.good.entity.Product;
import com.room.good.entity.ProductImage;

import com.room.good.repository.ProductImageRepository;
import com.room.good.repository.ProductRepository;


import javax.inject.Qualifier;
import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductImageRepository2 productImageRepository2;
    private final CategoryRepository categoryRepository;

    /*등록********************************************************************************/
    @Transactional
    @Override
    public Long register(ProductDTO productDTO) {

        Map<String, Object> entityMap = dtoToEntity(productDTO);
        Product product = (Product) entityMap.get("product");
        List<ProductImage> productImageList=(List<ProductImage>)entityMap.get("imgList");
        List<ProductImage2> productImageList2=(List<ProductImage2>)entityMap.get("imgList2");
        productRepository.save(product); // Product 저장

        productImageList.forEach(productImage -> {
            ProductImage save = productImageRepository.save(productImage);
        });
        productImageList2.forEach(productImage2 -> {
            ProductImage2 save = productImageRepository2.save(productImage2);
        });
        return product.getPno();
    }
    //// 수정/////////////
    @Transactional
    @Override
    public Long modify(ProductDTO productDTO) {

        Optional<Product> product1=productRepository.findById(productDTO.getPno());

        if(product1.isPresent()){
        Map<String, Object> entityMap = dtoToEntity(productDTO);
        Product product = (Product) entityMap.get("product");
        List<ProductImage> productImageList=(List<ProductImage>)entityMap.get("imgList");
        List<ProductImage2> productImageList2=(List<ProductImage2>)entityMap.get("imgList2");
        productRepository.save(product); // Product 저장

        productImageList.forEach(productImage -> {
            Optional<ProductImage> byId = productImageRepository.findById(productImage.getPinum());
            log.info("here11");
            if(byId.isPresent()) {
                ProductImage save = productImageRepository.save(byId.get());
                log.info("here1");
            }else {
                ProductImage save = productImageRepository.save(productImage);
            }
        });
        productImageList2.forEach(productImage2 -> {
            Optional<ProductImage2> byId2 = productImageRepository2.findById(productImage2.getPinum());
            log.info("here22");
            if(byId2.isPresent()) {
            ProductImage2 save2 = productImageRepository2.save(byId2.get());
                log.info("here2");
            }else {
                ProductImage2 save = productImageRepository2.save(productImage2);
            }
        });
        return product.getPno();
        } else {
            return null; // 혹은 다른 적절한 값을 반환할 수 있습니다.
        }
    }
    /////////////////////리드//////////////////////////////////
    @Transactional
    @Override
    public ProductDTO read(Long pno) {
        List<Object[]> result = productRepository.getProductAll(pno);
        log.info("result"+result);
        Product product=(Product) result.get(0)[0];

        List<ProductImage> productImageList=new ArrayList<>();
        result.forEach(arr->{
            ProductImage productImage=(ProductImage)arr[1];
            productImageList.add(productImage);
            log.info("productImage"+productImage);
        });
        log.info("productImageList");
        List<ProductImage2> productImageList2=new ArrayList<>();
        result.forEach(arr->{
            ProductImage2 productImage=(ProductImage2)arr[2];
            productImageList2.add(productImage);
        });

        log.info("productImageList"+productImageList);
        return entitiesToDTO(product, productImageList, productImageList2);
    }
    ///////////// 삭제///////////////////////////////////////////////////////
    @Transactional
    @Override
    public void remove(Long pno) {
        //글 삭제
        productRepository.deleteById(pno);
        //사진 삭제
        productImageRepository.deleteById(pno);
    }

    /*페이징*******************************************************************************/
    /*페이징*******************************************************************************/
    @Transactional
    @Override
    public PageResultDTO<ProductDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("pno").descending());// mno 기준으로 솔팅!
        Page<Object[]> result = productRepository.getListPage(pageable); // 리스트를 페이징처리해서 가져옴

        log.info("==============================================");
        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });

        Function<Object[], ProductDTO> fn = (arr -> entitiesToDTO(
                (Product) arr[0],
                (List<ProductImage>) Arrays.asList((ProductImage) arr[1]),
                (List<ProductImage2>) Arrays.asList((ProductImage2) arr[2])));
        // PageResultDTO<DTO,EN> pageResult = new PageResultDTO<>(result, fn);
        // return pageResult; 이거랑 같은 뜻임 . -> 즉 pageResult에 저 생성자 정보들
        return new PageResultDTO<>(result, fn);
        // 위에서 만들었던 펑션 -> <Object[], MovieDTO> 이 그릇을 가지고있는 fn과 result를 가지고 생성자를 호출중
    }

    @Transactional
    @Override
    public PageResultDTO<ProductDTO, Object[]> categoryPage(long cno, PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("pno").descending());
        Page<Object[]> result = productRepository.getListPageByCategory(cno, pageable);

        log.info("==============================================");
        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });

        Function<Object[], ProductDTO> fn = (arr -> entitiesToDTO(
                (Product) arr[0],
                (List<ProductImage>) Arrays.asList((ProductImage) arr[1]),
                (List<ProductImage2>) Arrays.asList((ProductImage2) arr[2])));

        return new PageResultDTO<>(result, fn);
    }

//    @Transactional
//    @Override
//    public PageResultDTO<ProductDTO, Object[]> getList(PageRequestDTO requestDTO) {
//
//        Page<Object[]> result = null;
//        if(requestDTO.getType()==null){
//            Pageable pageable = requestDTO.getPageable(Sort.by("pno").descending());// mno 기준으로 솔팅!
//            result = productRepository.getListPage(pageable); // 리스트를 페이징처리해서 가져옴
//        }else if (requestDTO.getType().equals("0")) {
//            result = productRepository.searchPageForAll(
//                    requestDTO.getType(),
//                    requestDTO.getKeyword(),
//                    requestDTO.getPageable(Sort.by("pno").descending()));
//        }else {
//            result = productRepository.searchPage(
//                    requestDTO.getType(),
//                    requestDTO.getKeyword(),
//                    requestDTO.getPageable(Sort.by("pno").descending()));
//        }
//        log.info("==============================================");
//        result.getContent().forEach(arr -> {
//            log.info(Arrays.toString(arr));
//        });
//
//        Function<Object[], ProductDTO> fn = (arr -> entitiesToDTO(
//
//                (Product) arr[0],
//                (List<ProductImage>) (Arrays.asList((ProductImage) arr[1]))));
//
//        return new PageResultDTO<>(result, fn);
//    }
//
//    @Transactional
//    @Override
//    public PageResultDTO<ProductDTO, Object[]> categoryPage(long cno, PageRequestDTO requestDTO) {
//
//        Page<Object[]> result =null;
//        if (requestDTO.getType() != null && requestDTO.getKeyword() != null) {
//            // 검색 조건이 있는 경우
//            if (requestDTO.getType() == null || requestDTO.getType().equals("0")) {
//                // 전체 검색인 경우
//                result = productRepository.searchPageForAll(
//                        requestDTO.getType(),
//                        requestDTO.getKeyword(), // 검색어 설정
//                        requestDTO.getPageable(Sort.by("pno").descending()) // 페이지와 정렬 설정
//                );
//            } else {
//                // 특정 타입에 따른 검색인 경우
//                result = productRepository.searchPage(
//                        requestDTO.getType(), // 타입 설정
//                        requestDTO.getKeyword(), // 검색어 설정
//                        requestDTO.getPageable(Sort.by("pno").descending()) // 페이지와 정렬 설정
//                );
//            }
//        } else {
//            // 검색 조건이 없는 경우
//            result = productRepository.getListPageByCategory(cno, requestDTO.getPageable(Sort.by("pno").descending()));
//        }
//        Function<Object[], ProductDTO> fn = (arr -> entitiesToDTO(
//                (Product) arr[0],
//                (List<ProductImage>) (Arrays.asList((ProductImage) arr[1])))
//        );
//
//        return new PageResultDTO<>(result, fn);
//    }



}