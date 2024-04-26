package com.room.good.service;

import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.PageResultDTO;
import com.room.good.dto.ProductDTO;
import com.room.good.entity.Product;
import com.room.good.entity.ProductImage;
import com.room.good.repository.ProductImageRepository;
import com.room.good.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    @Override
    public Long register(ProductDTO productDTO) {
        log.info("productDTOproductDTO"+productDTO);
            Map<String, Object> entityMap = dtoToEntity(productDTO);// map 형태로 들어가는 이유 -> dtoToEntity 보기!
            Product product = (Product) entityMap.get("product");
            // 보고왔으면 이건 Movie 엔티티라는걸 알게됨!dtoToEntity 를 보면 <String,Object>라서  Object 를 이렇게 형변환을 해줘야 됨

        List<ProductImage> poroductImageList=(List<ProductImage>)entityMap.get("imgList");
        System.out.println("poroductImageList: " + poroductImageList);

        productRepository.save(product); // movie insert
        poroductImageList.forEach(productImage -> {
            ProductImage save = productImageRepository.save(productImage);// movie image insert
            log.info(save+"sesavesave");
        });
            return product.getPno();
        }

    @Override
    public PageResultDTO<ProductDTO, Object[]> getList(PageRequestDTO requestDTO) {
        return null;
    }
}