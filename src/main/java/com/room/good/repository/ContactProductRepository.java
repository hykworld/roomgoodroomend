package com.room.good.repository;

import com.room.good.entity.ContactProduct;
import com.room.good.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactProductRepository extends JpaRepository<ContactProduct,Long> {
    @EntityGraph(attributePaths = {"clubMember"}, type = EntityGraph.EntityGraphType.FETCH)
    List<ContactProduct> findByProduct(Product product);

}
