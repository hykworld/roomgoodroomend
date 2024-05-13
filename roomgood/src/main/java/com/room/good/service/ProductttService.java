package com.room.good.service;

import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.PageResultDTO;
import com.room.good.dto.ProductDTO;
import com.room.good.dto.ProductImageDTO;
import com.room.good.entity.CategoryBig;
import com.room.good.entity.Product;
import com.room.good.entity.ProductImage;
import com.room.good.entity.ProductImage2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ProductttService {

    PageResultDTO<ProductDTO, Object[]> getListtt(PageRequestDTO requestDTO, String priceno); //목록 처리!


    default ProductDTO entitiesToDTO(Product product, List<ProductImage> productImages, List<ProductImage2> productImages2) {
        ProductDTO productDTO = ProductDTO.builder()
                .pno(product.getPno())
                .pname(product.getPname())
                .price(product.getPrice())
                .discount(product.getDiscount())
                .originalPrice(product.getOriginalPrice())
                .stock(product.getStock())
                .itemSellStatus(product.getItemSellStatus())
                .content(product.getContent())
                .subContent(product.getSubContent())
                .build();

        // 카테고리 정보 추가
        CategoryBig categoryBig = product.getCategoryBig();
        productDTO.setCategoryBig(categoryBig);

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

        List<ProductImageDTO> productImageDTOList2 = productImages2.stream().map(productImage2 -> {
            if (productImage2 != null) {
                return ProductImageDTO.builder()
                        .pinum(productImage2.getPinum())
                        .piuuid(productImage2.getPiuuid())
                        .piimgName(productImage2.getPiimgName())
                        .pipath(productImage2.getPipath())
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

        productDTO.setImageDTOList2(productImageDTOList2);

        return productDTO;
    }

    /*클라이언트에 받은 값을 엔티티로 바꿔서 db에 저장 ***********************************************************/
    default Map<String, Object> dtoToEntity(ProductDTO productDTO) {
        Map<String, Object> entityMap = new HashMap<>();

        Product product = Product.builder()
                .pno(productDTO.getPno())
                .pname(productDTO.getPname())
                .price(productDTO.getPrice())
                .discount(productDTO.getDiscount())
                .originalPrice(productDTO.getOriginalPrice())
                .stock(productDTO.getStock())
                .itemSellStatus(productDTO.getItemSellStatus())
                .content(productDTO.getContent())
                .subContent(productDTO.getSubContent())
                .build();

        CategoryBig categoryBig = productDTO.getCategoryBig(); // 카테고리 정보 가져오기
        product.setCategoryBig(categoryBig); // 제품에 카테고리 설정

        List<ProductImageDTO> imageDTOList = productDTO.getImageDTOList();

        if (imageDTOList != null && imageDTOList.size() > 0) {
            List<ProductImage> productImageList = imageDTOList.stream().map(productImageDTO -> {
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
        }

        List<ProductImageDTO> imageDTOList2 = productDTO.getImageDTOList2();

        if (imageDTOList2 != null && imageDTOList2.size() > 0) {
            List<ProductImage2> productImageList2 = imageDTOList2.stream().map(productImageDTO -> {
                ProductImage2 productImage2 = ProductImage2.builder()
                        .pinum(productImageDTO.getPinum())
                        .pipath(productImageDTO.getPipath())
                        .piimgName(productImageDTO.getPiimgName())
                        .piuuid(productImageDTO.getPiuuid())
                        .product(product)
                        .build();
                return productImage2;
            }).collect(Collectors.toList());
            entityMap.put("imgList2", productImageList2);
        }

        entityMap.put("product", product); // 엔티티 맵에 제품 추가
        return entityMap;
    }
    /*클라이언트에 받은 값을 엔티티로 바꿔서 db에 저장
     ***********************************************************************/

}