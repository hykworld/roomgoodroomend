//package com.room.good.repository;
//
//import com.room.good.dto.RatingSummaryDTO;
//import com.room.good.entity.Review;
//import io.lettuce.core.dynamic.annotation.Param;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//public interface RatingRepository extends JpaRepository<Review, Long> {
//
//    @Query("SELECT new com.example.RatingSummaryDTO(COUNT(r), AVG(r.grade)) " +
//            "FROM Review r " +
//            "WHERE r.product.pno = :pno")
//    RatingSummaryDTO findRatingSummaryByProductPno(@Param("pno") Long pno);
//}
