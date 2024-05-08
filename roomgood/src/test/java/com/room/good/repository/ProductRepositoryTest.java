package com.room.good.repository;

import com.room.good.constant.ItemSellStatus;
import com.room.good.entity.Product;
import com.room.good.entity.ProductImage;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;
import java.util.stream.IntStream;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductImageRepository productImageRepository;




    @Test
    public void insertProductTestSooMin() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            Product product = Product.builder().pname("물건"+i).price(10000L).stock(0L).content("hello").subContent("hi").itemSellStatus(ItemSellStatus.SELL).build();

            System.out.println("------------------------------------------");

            productRepository.save(product);

            int count = (int)(Math.random() * 5) + 1; //1,2,3,4


            for(int j = 0; j < count; j++){
                ProductImage productImage = ProductImage.builder()
                        .piuuid(UUID.randomUUID().toString())
                        .piimgName("test"+j+".jpg")
                        .pipath("/img/")
                        .product(product).build();

                productImageRepository.save(productImage);
            }


            System.out.println("===========================================");

        });
    }



}