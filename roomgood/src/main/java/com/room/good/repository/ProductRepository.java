package com.room.good.repository;


import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.PageResultDTO;
import com.room.good.dto.ProductDTO;
import com.room.good.entity.CategoryBig;
import com.room.good.entity.Product;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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



    @Query("select p, pi from Product p " +
            "left outer join ProductImage pi on pi.product = p" +
            " group by p ")
    Page<Object[]> getListPage(Pageable pageable);
    // select Movie.* ,MovieImage.* ,  avg(coalesce(Review.grade,0)), count(Review.rno)
    // from Movie left outer join MovieImage on Movie.mno = MovieImage.movie_mno
    // left outer join review on  Movie.bno = review.movie_mno
    // group by movie.mno;


    // 카테고리에 따라 상품 목록을 가져오는 메서드
    @Query("SELECT p, pi FROM Product p " +
            "LEFT JOIN ProductImage pi ON pi.product = p " +
            "WHERE p.categoryBig.cno = :cno " + // 카테고리 식별자로 필터링
            "GROUP BY p")
    Page<Object[]> getListPageByCategory(@Param("cno") Long cno, Pageable pageable);

    @Query("SELECT p, pi FROM Product p " +
            "LEFT JOIN ProductImage pi ON pi.product = p " +
            "WHERE p.categoryBig.cno = :type and p.pname like %:keyword% " +
            "GROUP BY p")
    Page<Object[]> searchPage(String type,@Param("keyword")  String keyword, Pageable pageable);

    @Query("SELECT p, pi FROM Product p " +
            "LEFT JOIN ProductImage pi ON pi.product = p " +
            "WHERE (coalesce(:type, '0') = '0' OR p.categoryBig.cno = :type) " + // 변경된 부분
            "AND p.pname LIKE %:keyword% " +
            "GROUP BY p")
    Page<Object[]> searchPageForAll(@Param("type") String type, @Param("keyword") String keyword, Pageable pageable);

    @Query("select p, pi from Product p left join ProductImage pi on pi.product = p where p.pno = :pno")
    List<Object[]> getProduct(Long pno);

}
