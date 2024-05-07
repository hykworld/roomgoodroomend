package com.room.good.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(exclude = {"clubMember","cartItems"})
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno; // 프라이머리 키
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE) // 프라이머리키 지워지면 얘네도 다 지워지는 -> 부모 지워지면 자식들도 지워지는 에너테이션
    private ClubMember clubMember; // 회원

    private int quantity; // 수량

    @Builder.Default
    @OneToMany(mappedBy = "cart") // cart가 One
    private List<CartItem> cartItems = new ArrayList<>(); // 상품목록
    // CartItem엔티티에 있는 CartItem타입으로 배열을 만든다


    public Cart(ClubMember clubMember) {
        this.clubMember = clubMember;
    }

}