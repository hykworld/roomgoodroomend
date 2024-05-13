package com.room.good.repository;



import com.room.good.entity.Product;
import com.room.good.entity.ProductImage;
import com.room.good.entity.ProductImage2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductImageRepository2 extends JpaRepository<ProductImage2,Long> {
    // save() 저장, findAll() 조회, FindById() 아이디로 조회, delete() 삭제 가 기본값
    // count() entity 총 갯수 반환

}
