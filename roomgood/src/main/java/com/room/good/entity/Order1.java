package com.room.good.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(exclude = {"clubMember"})
public class Order1 extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ono; // 주문번호
    private String status; // 상태

// 상품준비중(판매자) // 배송 중(택배) // 배송 완료(택배)
// 구매 확정(배송완료 후 3일 이내 고객이 확인 안 누르면 자동으로 넘어감)
// 환불(7일이내 환불 안 하면 권한 삭제)변환 // 취소 // 반품 ====== 어드민이 관리

    private Date desireddate; // 배송희망일
    private String recipient; // 수령인

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ClubMember clubMember;

    private Long price; // 가격 꼭 넣어라
}
