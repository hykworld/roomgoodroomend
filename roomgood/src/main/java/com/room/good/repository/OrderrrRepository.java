package com.room.good.repository;

import com.querydsl.core.types.Order;
import com.room.good.entity.Order1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderrrRepository extends JpaRepository<Order1,Long> {

    List<Order1> findByClubMemberId(Long id);
}
