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
@NoArgsConstructor
@AllArgsConstructor
public class CartttDTO { // 장바구니에 필요한 컬럼
    private Long cno; // 카트번호

    private Long clubMemberId; // 유저번호

    private int quantity; // 총 수량 알기위해

    @Builder.Default
    private List<CartItem> cartItems = new ArrayList<>(); // 상품목록

}
