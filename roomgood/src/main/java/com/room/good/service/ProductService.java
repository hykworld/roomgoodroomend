package com.room.good.service;

import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.PageResultDTO;
import com.room.good.dto.ProductDTO;
import com.room.good.dto.ProductImageDTO;
import com.room.good.entity.Product;
import com.room.good.entity.ProductImage;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public interface ProductService {
        Long register(ProductDTO productDTO);

        // 아직 손대기 전
        PageResultDTO<ProductDTO, Object[]> getList(PageRequestDTO requestDTO); //목록 처리!
        // getList(PageRequestDTO requestDTO)을 통해
        // PageResultDTO<MovieDTO, Object[]>의 객체 만들기

        //주어진 페이지 요청 정보에 따라 페이지 결과 DTO를 가져오는 getList 메서드입니다.
        //getList 메서드는 PageRequestDTO를 매개변수로 받아서 PageResultDTO<MovieDTO, Object[]>를 반환합니다.
        //즉, 이 메서드는 페이지 요청 정보에 따라 페이징된 영화 목록을 가져와서
        // 해당 페이지의 결과를 PageResultDTO<MovieDTO, Object[]> 형태로 반환합니다.


        default Map<String, Object> dtoToEntity(ProductDTO productDTO) {
                Map<String, Object> entityMap = new HashMap<>();//해쉬맵형태로 entityMap생성

                Product product = Product.builder()
                        .pname(productDTO.getPname())
                        .price(productDTO.getPrice())
                        .stock(productDTO.getStock())
                        .itemSellStatus(productDTO.getItemSellStatus())
                        .content(productDTO.getContent())
                        .subContent(productDTO.getSubContent())
                        .build();
//----------- 제품정보------------------------------>
                entityMap.put("product", product);//위에서 만든 movie 는 "movie"라는 키값으로 연결!
//----------- 제품사진------------------------------>
                List<ProductImageDTO> imageDTOList = productDTO.getImageDTOList();

                System.out.println("여기가 문제네"+imageDTOList);
                System.out.println("listlistlistlist "+imageDTOList);

                if (imageDTOList != null && imageDTOList.size() > 0) {
                        List<ProductImage> productImageList = imageDTOList.stream().map(productImageDTO -> {
                                //그 짝짝꿍 또나옴 리스트형태를 다듬어야할 때stream().map().collect(Collectors.toList()); 얘네 또나옴
                                ProductImage productImage = ProductImage.builder()
                                        .pipath(productImageDTO.getPipath())
                                        .piimgName(productImageDTO.getPiimgName())
                                        .piuuid(productImageDTO.getPiuuid())
                                        .product(product)
                                        .build();
                                return productImage;
                        }).collect(Collectors.toList());
                        entityMap.put("imgList", productImageList);
                }
                return entityMap;

        }

}