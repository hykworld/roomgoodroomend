package com.room.good.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
//
@Data
@Builder
//@AllArgsConstructor
@NoArgsConstructor
class CartItemDTO {
    private Long cino; // 장바구니 아이템 고유번호
    private Long pno; // 상품번호
    private String pname; // 상품이름
    private String pipath; // 상품 이미지 경로
    private int quantity; // 총 수량
    private int price; // 금액

    // 생성자
    public CartItemDTO(Long cino, Long pno, String pname, String pipath, int quantity, int price) {
        this.cino = cino;
        this.pno = pno;
        this.pname = pname;
        this.pipath = pipath;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters (omitted for brevity)
}
