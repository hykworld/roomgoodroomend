package com.room.good.service;

import com.room.good.dto.CartDTO;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    //장바구니화면의 목록
    @Override
    public Long cartList(CartDTO cartDTO, Long clubMemberId) {
        Long memberId = cartDTO.getClubMemberId(); // 멤버아이디를 정보를 가지고 와서 memberId에 넣는다
        // 장바구니에 들어간 상품을 cartDTO에 있는 cartitem에 넣는다

        return null;
    }
}
