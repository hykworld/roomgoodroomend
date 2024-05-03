package com.room.good.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {

    private Long pno; // 상품번호

    private int quantity; // 총 수량 알기위해

    private int price; // 금액

    private String pipath; // 상품 이미지 경로

    private String pname; // 상품이름

    public CartItemDTO(Long pno, String pname, int quantity, int price, String pipath){ // 생성자 (인자 이걸로 받을거고)
        // 받아서 여기 dto로 넣어주겠다
        this.pno = pno;
        this.quantity = quantity;
        this.price = price;
        this.pipath = pipath;
        this.pname = pname;
    }

}
