package com.room.good.repository;

import com.room.good.entity.Cart;
import com.room.good.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByClubMemberId(Long clubMemberId);
    // 해당 아이디를 찾아서 그 아이디가 장바구니에 담은 아이템리스트를 가져온다
}
