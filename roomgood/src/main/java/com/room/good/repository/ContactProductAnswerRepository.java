package com.room.good.repository;

import com.room.good.entity.ContactProduct;
import com.room.good.entity.ContactProductAnswer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactProductAnswerRepository extends JpaRepository<ContactProductAnswer,Long> {

    @EntityGraph(attributePaths = {"contactProduct"},type = EntityGraph.EntityGraphType.FETCH)
    List<ContactProductAnswer> findByContactProduct(ContactProduct contactProduct);
}
