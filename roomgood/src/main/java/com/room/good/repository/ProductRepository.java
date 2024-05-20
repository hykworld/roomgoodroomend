package com.room.good.repository;



import com.room.good.dto.RatingSummaryDTO;
import com.room.good.entity.Product;
import com.room.good.entity.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    // save() 저장, findAll() 조회, FindById() 아이디로 조회, delete() 삭제 가 기본값
    // count() entity 총 갯수 반환


    @Query("SELECT p, pi, pi2 FROM Product p " +
            "LEFT JOIN ProductImage pi ON pi.product = p " +
            "LEFT JOIN ProductImage2 pi2 ON pi2.product = p " +
            "WHERE p.pno = :pno " +
            "GROUP BY pi")
    List<Object[]> getProductAll(Long pno);
   /* SELECT p.*, pi.*
    FROM Product p
    LEFT OUTER JOIN product_image pi ON pi.product_pno = p.pno
    WHERE p.pno = pno;*/



    @Query("SELECT p, pi, pi2, COUNT(p) FROM Product p " +
            "LEFT JOIN ProductImage pi ON pi.product = p " +
            "LEFT JOIN ProductImage2 pi2 ON pi2.product = p " +
            " group by p ")
    Page<Object[]> getListPage(Pageable pageable);
    // select Movie.* ,MovieImage.* ,  avg(coalesce(Review.grade,0)), count(Review.rno)
    // from Movie left outer join MovieImage on Movie.mno = MovieImage.movie_mno
    // left outer join review on  Movie.bno = review.movie_mno
    // group by movie.mno;



    //메인 창에 적용시킬 JPA 비동기 페이징처리 위함
    Page<Product> findAll(Pageable pageable);
    //메인 창 페이징 처리 위함
    @Query("select count(pd) from Product pd")
    Long countByAll();

    // 카테고리에 따라 상품 목록을 가져오는 메서드
    @Query("SELECT p, pi, pi2 FROM Product p " +
            "LEFT JOIN ProductImage pi ON pi.product = p " +
            "LEFT JOIN ProductImage2 pi2 ON pi2.product = p " +
            "WHERE p.categoryBig.cno = :cno " +
            " group by p ")
    Page<Object[]> getListPageByCategory(@Param("cno") Long cno, Pageable pageable);

    @Query("select p, pi from Product p left join ProductImage pi on pi.product = p where p.pno = :pno")
    List<Object[]> getProduct(Long pno);

    @Query("SELECT p, pi, pi2 FROM Product p " +
            "LEFT OUTER JOIN ProductImage pi ON pi.product = p " +
            "LEFT OUTER JOIN ProductImage2 pi2 ON pi2.product = p " + // 추가
            "WHERE p.pname LIKE %:keyword% " +
            "GROUP BY p")
    Page<Object[]> findByPnameContaining(@Param("keyword") String keyword, Pageable pageable);

    // ProductRepository.java 에 쿼리 추가
    @Query("SELECT COUNT(*) FROM Product")
    long getProductCount();

    //박수민 추가

    @Query("select avg(coalesce(r.grade,0)),  count(r)" +
            " from Product p left outer join Review r on r.product = p" +
            " where p.pno = :pno group by p")
    List<Object[]> getProductWithReview(Long pno);

    @Query("select count(r)" +
            " from Product p left outer join Review r on r.product = p" +
            " where p.pno = :pno" +
            " and r.grade = 1 group by p")
    List<Object[]> getProductWithOneGrade(Long pno);

    @Query("select count(r)" +
            " from Product p left outer join Review r on r.product = p" +
            " where p.pno = :pno" +
            " and r.grade = 2 group by p")
    List<Object[]> getProductWithTwoGrade(Long pno);

    @Query("select count(r)" +
            " from Product p left outer join Review r on r.product = p" +
            " where p.pno = :pno" +
            " and r.grade = 3 group by p")
    List<Object[]> getProductWithThreeGrade(Long pno);

    @Query("select count(r)" +
            " from Product p left outer join Review r on r.product = p" +
            " where p.pno = :pno" +
            " and r.grade = 4 group by p")
    List<Object[]> getProductWithFourGrade(Long pno);

    @Query("select count(r)" +
            " from Product p left outer join Review r on r.product = p" +
            " where p.pno = :pno" +
            " and r.grade = 5 group by p")
    List<Object[]> getProductWithFiveGrade(Long pno);
}

