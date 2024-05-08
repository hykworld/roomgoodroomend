package com.room.good.repository;

import com.room.good.entity.ContactProduct;
import com.room.good.entity.Product;
import com.room.good.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    @EntityGraph(attributePaths = {"clubMember"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByProduct(Product product, Pageable pageable);

    @Query("select count(rv) from Review rv where rv.product = :product")
    Long countByProduct(Product product);
}
