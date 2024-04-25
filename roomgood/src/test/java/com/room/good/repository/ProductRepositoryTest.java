package com.room.good.repository;

import com.room.good.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품 저장 테스트")
    public void createProductTest(){
        Product product= new Product();
        product.setPname("테스트 상품");
        product.setPrice(10000L);
        product.setContent("테스트");
        product.setSubContent("테스트 서브");
        product.setStock(1l);
        Product savedProduct =productRepository.save(product);

        System.out.println(savedProduct.toString());

    }

}