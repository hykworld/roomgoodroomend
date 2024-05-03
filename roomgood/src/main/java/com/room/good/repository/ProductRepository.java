package com.room.good.repository;


import com.room.good.entity.Product;
import com.room.good.entity.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    // save() 저장, findAll() 조회, FindById() 아이디로 조회, delete() 삭제 가 기본값
    // count() entity 총 갯수 반환

    @Query("select p, pi" +
            " from Product p left outer join ProductImage pi on pi.product = p " +
            " where p.pno = :pno " +
            "group by pi")
    List<Object[]> getProductAll(Long pno);
   /* SELECT p.*, pi.*
    FROM Product p
    LEFT OUTER JOIN product_image pi ON pi.product_pno = p.pno
    WHERE p.pno = pno;*/


<<<<<<< HEAD
=======

>>>>>>> 8670f751e87aaa765f280cc5004f10385d44571f
    @Query("select p, pi from Product p " +
            "left outer join ProductImage pi on pi.product = p" +
            " group by p ")
    Page<Object[]> getListPage(Pageable pageable);
    // select Movie.* ,MovieImage.* ,  avg(coalesce(Review.grade,0)), count(Review.rno)
    // from Movie left outer join MovieImage on Movie.mno = MovieImage.movie_mno
    // left outer join review on  Movie.bno = review.movie_mno
    // group by movie.mno;
<<<<<<< HEAD
=======

>>>>>>> 8670f751e87aaa765f280cc5004f10385d44571f
}
