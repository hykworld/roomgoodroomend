package com.room.good.repository;

import com.room.good.entity.ContactProduct;
import com.room.good.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactProductRepository extends JpaRepository<ContactProduct,Long> {
    @EntityGraph(attributePaths = {"clubMember"}, type = EntityGraph.EntityGraphType.FETCH)
    List<ContactProduct> findByProduct(Product product, Pageable pageable);

    @Query("select count(cp) from ContactProduct cp where cp.product = :product")
    Long countByProduct(@Param("product") Product product);

}
