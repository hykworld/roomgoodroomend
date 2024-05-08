package com.room.good.repository;

import com.room.good.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartttItemRepository extends JpaRepository<CartItem,Long> {

    Optional<CartItem> findByProductPno(Long pno);
    Optional<CartItem> findByProductPnoAndCartCno(Long pno,Long Cno);

    List<CartItem> findByCartCno(Long cno);


}
