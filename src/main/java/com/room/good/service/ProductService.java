package com.room.good.service;


import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.PageResultDTO;
import com.room.good.dto.ProductDTO;
import com.room.good.dto.ProductImageDTO;
import com.room.good.entity.Product;
import com.room.good.entity.ProductImage;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public interface ProductService {

        /////////글등록////////////////////////////////////////////////////////
        Long register(ProductDTO productDTO);

        /////////수정////////////////////////////////////////////////////////
        Long modify(ProductDTO productDTO);
        /////////읽기////////////////////////////////////////////////////////
        ProductDTO read(Long pno);

        /////////읽기////////////////////////////////////////////////////////
        void remove(Long pno);
        //////// 페이징 처리///////////////////////////////////////////////////
        PageResultDTO<ProductDTO, Object[]> getList(PageRequestDTO requestDTO); //목록 처리!
        // getList(PageRequestDTO requestDTO)을 통해
        // PageResultDTO<MovieDTO, Object[]>의 객체 만들기

        //주어진 페이지 요청 정보에 따라 페이지 결과 DTO를 가져오는 getList 메서드입니다.
        //getList 메서드는 PageRequestDTO를 매개변수로 받아서 PageResultDTO<MovieDTO, Object[]>를 반환합니다.
        //즉, 이 메서드는 페이지 요청 정보에 따라 페이징된 영화 목록을 가져와서
        // 해당 페이지의 결과를 PageResultDTO<MovieDTO, Object[]> 형태로 반환합니다.

        //////// 페이징 처리///////////////////////////////////////////////////

        /*서버의 요청으로 db에 저장된 값을 뷰로 꺼내기 위해 엔티티를 dto로 만듬
         ***********************************************************************/
        default ProductDTO entitiesToDTO(Product product, List<ProductImage> productImages) {
                ProductDTO productDTO = ProductDTO.builder()
                        .pno(product.getPno())
                        .pname(product.getPname())
                        .price(product.getPrice())
                        .stock(product.getStock())
                        .itemSellStatus(product.getItemSellStatus())
                        .content(product.getContent())
                        .subContent(product.getSubContent())
                        .build();

                List<ProductImageDTO> productImageDTOList = productImages.stream().map(productImage -> {
                        if (productImage != null) {
                                return ProductImageDTO.builder()
                                        .pinum(productImage.getPinum())
                                        .piuuid(productImage.getPiuuid())
                                        .piimgName(productImage.getPiimgName())
                                        .pipath(productImage.getPipath())
                                        .build();
                        } else {
                                // 대체 이미지 정보 설정
                                return ProductImageDTO.builder()
                                        .piuuid("default") // 대체 이미지의 UUID 또는 다른 식별자
                                        .piimgName("noimage.png") // 대체 이미지 파일명
                                        .pipath("img/noimage.png") // 대체 이미지 경로
                                        .build();
                        }
                }).collect(Collectors.toList());

                productDTO.setImageDTOList(productImageDTOList);

                return productDTO;
        }

        /*클라이언트에 받은 값을 엔티티로 바꿔서 db에 저장 ***********************************************************/
        default Map<String, Object> dtoToEntity(ProductDTO productDTO) {
                Map<String, Object> entityMap = new HashMap<>();//해쉬맵형태로 entityMap생성

                Product product = Product.builder()
                        .pno(productDTO.getPno())
                        .pname(productDTO.getPname())
                        .price(productDTO.getPrice())
                        .stock(productDTO.getStock())
                        .itemSellStatus(productDTO.getItemSellStatus())
                        .content(productDTO.getContent())
                        .subContent(productDTO.getSubContent())
                        .build();
                //----------- 제품정보------------------------------>
                System.out.println("productproduct"+product);
                entityMap.put("product", product);//위에서 만든 movie 는 "movie"라는 키값으로 연결!
                //----------- 제품사진------------------------------>
                List<ProductImageDTO> imageDTOList = productDTO.getImageDTOList();
                System.out.println("sdfsdf"+imageDTOList);
                if (imageDTOList != null && imageDTOList.size() > 0) {
                        List<ProductImage> productImageList = imageDTOList.stream().map(productImageDTO
                                -> {
                                ProductImage productImage = ProductImage.builder()
                                        .pinum(productImageDTO.getPinum())
                                        .pipath(productImageDTO.getPipath())
                                        .piimgName(productImageDTO.getPiimgName())
                                        .piuuid(productImageDTO.getPiuuid())
                                        .product(product)
                                        .build();
                                return productImage;
                        }).collect(Collectors.toList());
                        entityMap.put("imgList", productImageList);
                        System.out.println("bbbbbbbbbbb"+productImageList);
                }
                return entityMap;

        }
        /*클라이언트에 받은 값을 엔티티로 바꿔서 db에 저장
         ***********************************************************************/

}