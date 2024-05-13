package com.room.good.repository;

import com.room.good.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductttRepository extends JpaRepository<Product,Long> {

    @Query("SELECT p, pi, pi2 FROM Product p " +
            "LEFT JOIN ProductImage pi ON pi.product = p " +
            "LEFT JOIN ProductImage2 pi2 ON pi2.product = p" +
            " where p.price < 10000 " +
            "group by p")
    Page<Object[]> getListcheapPageAll(Pageable pageable);

    @Query("SELECT p, pi, pi2 FROM Product p " +
            "LEFT JOIN ProductImage pi ON pi.product = p " +
            "LEFT JOIN ProductImage2 pi2 ON pi2.product = p" +
            " where p.price < 1999 " +
            "group by p")
    Page<Object[]> getListcheapPage1000(Pageable pageable);
    @Query("SELECT p, pi, pi2 FROM Product p " +
            "LEFT JOIN ProductImage pi ON pi.product = p " +
            "LEFT JOIN ProductImage2 pi2 ON pi2.product = p" +
            " where p.price > 2000 and p.price <4000 " +
            "group by p")
    Page<Object[]> getListcheapPage3000(Pageable pageable);
    @Query("SELECT p, pi, pi2 FROM Product p " +
            "LEFT JOIN ProductImage pi ON pi.product = p " +
            "LEFT JOIN ProductImage2 pi2 ON pi2.product = p" +
            " where p.price > 4000 and p.price <6000 " +
            "group by p")
    Page<Object[]> getListcheapPage5000(Pageable pageable);

    @Query("SELECT p, pi, pi2 FROM Product p " +
            "LEFT JOIN ProductImage pi ON pi.product = p " +
            "LEFT JOIN ProductImage2 pi2 ON pi2.product = p" +
            " where p.price > 6000 and p.price <8000 " +
            "group by p")
    Page<Object[]> getListcheapPage7000(Pageable pageable);

    @Query("SELECT p, pi, pi2 FROM Product p " +
            "LEFT JOIN ProductImage pi ON pi.product = p " +
            "LEFT JOIN ProductImage2 pi2 ON pi2.product = p" +
            " where p.price > 8000 and p.price <10000 " +
            "group by p")
    Page<Object[]> getListcheapPage9000(Pageable pageable);


}
