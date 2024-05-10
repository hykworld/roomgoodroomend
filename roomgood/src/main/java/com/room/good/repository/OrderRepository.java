package com.room.good.repository;

import com.room.good.entity.Order1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order1,Long> {
}
