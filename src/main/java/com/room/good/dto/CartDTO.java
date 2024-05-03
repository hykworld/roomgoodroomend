package com.room.good.dto;

import com.room.good.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    // 장바구니에 필요한건 List<CartItemDTO>, 장바구니 아이디

    private Long cno; // 장바구니 고유번호

    private int totalPrice; // 총 가격


//    List<CartItemDTO> cartItemDTOList = new ArrayList<>();
    @Builder.Default
    private List<CartItem> cartItems = new ArrayList<>(); // 상품목록
}
