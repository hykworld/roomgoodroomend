package com.room.good.service;


import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.PageResultDTO;
import com.room.good.dto.ProductDTO;
import com.room.good.dto.ProductImageDTO;
import com.room.good.entity.CategoryBig;
import com.room.good.entity.CategoryMiddle;
import com.room.good.entity.Product;
import com.room.good.entity.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


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
        /////////제거////////////////////////////////////////////////////////
        void remove(Long pno);
        //////// 페이징 처리///////////////////////////////////////////////////
        PageResultDTO<ProductDTO, Object[]> getList(PageRequestDTO requestDTO); //목록 처리!
        //////// 카테고리///////////////////////////////////////////////////
        PageResultDTO<ProductDTO, Object[]> categoryPage(long cno, PageRequestDTO requestDTO);

        //////// 엔티티 투 디티오///////////////////////////////////////////////////
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

                return productDTO;
        }

        /*클라이언트에 받은 값을 엔티티로 바꿔서 db에 저장 ***********************************************************/
        default Map<String, Object> dtoToEntity(ProductDTO productDTO) {
                Map<String, Object> entityMap = new HashMap<>();

                Product product = Product.builder()
                        .pno(productDTO.getPno())
                        .pname(productDTO.getPname())
                        .price(productDTO.getPrice())
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

                entityMap.put("product", product); // 엔티티 맵에 제품 추가
                return entityMap;
        }

        /*클라이언트에 받은 값을 엔티티로 바꿔서 db에 저장
         ***********************************************************************/

}