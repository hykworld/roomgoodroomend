package com.room.good.repository;

import com.room.good.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderrrItemRepository extends JpaRepository<OrderItem,Long> {

    List<OrderItem> findByOrder1Ono(Long ono);
}
