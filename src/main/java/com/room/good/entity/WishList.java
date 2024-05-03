package com.room.good.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wno;

    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)/// 멤버의 고유id를 레퍼런스
    private ClubMember clubMember;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;




// 상품과의 관계는 다대다 아닌가?
// 그런 중간에 뭘 넣야 되나?
}


