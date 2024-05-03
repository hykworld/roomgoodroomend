package com.room.good.entity;
import com.room.good.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(exclude = {"clubMember"})
@Table(name = "orders")
public class Order1 extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ono; // 주문번호

    @Enumerated(EnumType.STRING)
    private OrderStatus OrderStatus; // 상태
// 상품준비중(판매자) // 배송 중(택배) // 배송 완료(택배)
// 구매 확정(배송완료 후 3일 이내 고객이 확인 안 누르면 자동으로 넘어감)
// 환불(7일이내 환불 안 하면 권한 삭제)변환 // 취소 // 반품 ====== 어드민이 관리
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClubMember clubMember;

    private Date desireddate; // 배송희망일
    private String recipient; // 수령인

    // 연관관계의 주인은 외래키가 있는 곳으로 설정
    // 주인이 아닌쪽은 연관관계 매핑 시 mappedBy 속성의 값으로 연관 관계의 주인을 설정
    // 주인이 아닌 쪽은 읽기만 가능



    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    //////////////////종효 주문/////////////////////////

    @OneToMany(mappedBy = "order1", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) { // 상품정보 담아줌
        orderItems.add(orderItem);
        orderItem.setOrder1(this); // 오더랑 오더아이템 양방향 참조 관계니까 orderItem에도 order객체 세팅
    }

    public static Order1 createOrder(ClubMember clubmember, List<OrderItem> orderItems) {
        Order1 order1 = new Order1();
        order1.setClubMember(clubmember); // 상품주문한 회원 세팅
        for(OrderItem orderItem : orderItems) { // 여러개 주문할수 있으니까 리스트형식으로 해서 for문 돌려서넣어줌
            order1.addOrderItem(orderItem);
        }

        order1.setOrderStatus(com.room.good.constant.OrderStatus.ORDER); // 주문상태 order로 세팅함
        order1.setRegTime(LocalDateTime.now()); // 현재 시간으로 주문시간 세팅
        return order1;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for(OrderItem orderItem : orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }



}
