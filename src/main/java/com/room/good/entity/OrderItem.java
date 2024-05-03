package com.room.good.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(exclude = {"order1"})
public class OrderItem extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long oino; // 넘버 // 프라이머리 키

    @ManyToOne(fetch = FetchType.LAZY) // 하나의 상품에 여러개가 들어갈수 있으니까 ManyToOne
    @JoinColumn(name = "product_id") // 레퍼런스 키
    private Product pno; // 상품 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "order_id") // 레퍼런스 키
    private Order1 order1;

    private Long orderPrice; // 제품 가격 api에서 뭘 줄지 몰라서 일단 넣어놓음?

    private Long count; // 수량 플러스 할 때 재고와는 다름

    private Long totalitemprice; // 총가격 멤버쉽 등급 올리기용이나 다른 부분에서 쓸듯


    ///////////////종효 주문////////////////////////
    public static OrderItem createOrderItem(Product product, Long count){ // 주문 할 경우 세팅
        OrderItem orderItem = new OrderItem();
        orderItem.setPno(product);
        orderItem.setCount(count);
        orderItem.setOrderPrice(product.getPrice());

        product.removeStock(count); // 주문수량만큼 재고수량 감소
        return orderItem;
    }

    public Long getTotalPrice(){ // 주문 수량이랑 가격 곱하기 = 총 수량
        return orderPrice*count;
    }


}
