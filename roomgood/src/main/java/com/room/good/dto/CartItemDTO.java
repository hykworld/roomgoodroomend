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
//public class CartItemDTO {
//
//    private Long pno; // 상품번호
//
//    private int quantity; // 총 수량 알기위해
//
//    private int price; // 금액
//
//    private String pipath; // 상품 이미지 경로
//
//    private String pname; // 상품이름
//
//    public CartItemDTO(Long pno, String pname, int quantity, int price, String pipath){ // 생성자 (인자 이걸로 받을거고)
//        // 받아서 여기 dto로 넣어주겠다
//        this.pno = pno;
//        this.quantity = quantity;
//        this.price = price;
//        this.pipath = pipath;
//        this.pname = pname;
//    }
//
//}




// 장바구니 아이템을 나타내는 클래스
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
