package com.room.good.service;

import com.room.good.dto.CartDTO;
import com.room.good.dto.CartItemDTO;

import java.util.List;

public interface CartService {

    // 상품 장바구니에 담기
    Long addCartList(CartItemDTO cartItemDTO, String email);

    // 상품목록 조회
    List<CartDTO> getCartList(String email);

    // 장바구니에 있는 상품의 수량 업데이트
    void updateCartItemCount(Long pno, int quantity);

    // 장바구니에 있는 상품 삭제
    void deleteCartItem(Long pno);

}

