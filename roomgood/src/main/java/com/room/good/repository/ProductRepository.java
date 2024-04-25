package com.room.good.repository;


import com.room.good.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    // save() 저장, findAll() 조회, FindById() 아이디로 조회, delete() 삭제 가 기본값
    // count() entity 총 갯수 반환

    List<Product> findByProductPname(String pname);


}
