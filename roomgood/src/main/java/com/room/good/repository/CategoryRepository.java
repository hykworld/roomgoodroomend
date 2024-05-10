package com.room.good.repository;

import com.room.good.entity.CategoryBig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface  CategoryRepository extends JpaRepository<CategoryBig, Long> {

//    @Query("SELECT c, cm " +
//            "FROM CategoryBig c LEFT OUTER JOIN c.categoryMiddles cm " +
//            "WHERE c.cbno = :cbno")
//    List<Object[]> getCategory(Long cbno);



}

