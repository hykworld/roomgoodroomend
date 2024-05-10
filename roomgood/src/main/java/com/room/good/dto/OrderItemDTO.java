package com.room.good.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemDTO {
    private Long oino; // 넘버
    private Long itemno; // 아이템 번호
    private String proname; // 이름,
    private Long itemprice; // 제품 가격 api에서 뭘 줄지 몰라서 일단 넣어놓음?
    private Long count; // 수량 플러스 할 때 재고와는 다름
    private Long totalitemprice; // 총가격 멤버쉽 등급 올리기용이나 다른 부분에서 쓸듯

}
