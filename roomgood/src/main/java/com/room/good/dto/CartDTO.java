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


    //        List<CartItemDTO> cartItemDTOList = new ArrayList<>();
    @Builder.Default
    private List<CartItem> cartItems = new ArrayList<>(); // 상품목록


    ///////////////////////////////////////////////////////////////////////
    private Long pno; // 상품번호

    private int quantity; // 총 수량 알기위해

    private int price; // 금액

    private String pipath; // 상품 이미지 경로

    private String pname; // 상품이름

    public CartDTO(Long pno, String pname, int quantity, int price, String pipath){ // 생성자 (인자 이걸로 받을거고)
        // 받아서 여기 dto로 넣어주겠다
        this.pno = pno;
        this.quantity = quantity;
        this.price = price;
        this.pipath = pipath;
        this.pname = pname;
    }
}
