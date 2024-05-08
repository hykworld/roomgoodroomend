package com.room.good.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.jackson.JsonComponent;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
<<<<<<< HEAD
@ToString(exclude = {"clubMember","cartItems"})
=======
@ToString
//(exclude = {"clubMember"})
@Table(name = "cart")
>>>>>>> 319b1cf89e84878f441a57cf9ec21c6d7e1bb906
public class Cart extends BaseEntity {
    // 장바구니 엔티티에 필요한건 장바구니 고유번호, 회원고유번호, 카트 새로 만드는 메서드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id") // 테이블컬럼이름이 cart_id
    private Long cno; // 프라이머리 키  카트 고유번호

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE) // 프라이머리키 지워지면 얘네도 다 지워지는 -> 부모 지워지면 자식들도 지워지는 에너테이션
    @JoinColumn(name = "club_member_id") // ClubMember와 관계를 맺고있고 ClubMember의 주 PK를 참조하도록 할라면 써야함
    private ClubMember clubMember; // 회원

    @Builder.Default
    @OneToMany(mappedBy = "cart") // cart가 One
    private List<CartItem> cartItems = new ArrayList<>(); // 상품목록
    // CartItem엔티티에 있는 CartItem타입으로 배열을 만든다


    public Cart(ClubMember clubMember) {
        this.clubMember = clubMember;
    }

    public static Cart createCart (ClubMember clubMember){ // 유저당 하나의 장바구니만 있어야 하기 때문에 유저당 하나씩 만드는것
        Cart cart = new Cart();
        cart.setClubMember(clubMember);
        return cart;
    }
}

// OneToMany를 사용한 이유 == Cart와 관련된 CartItem을 쉽게 가져올수 있음,,
// cascade = CascadeType.ALL == 부모엔티티의 상태변경을 자식엔티티에 전파하는 방식,, 부모엔티티에 대한 모든변경이 자식엔티티에 적용
// orphanRemoval = true == 부모 엔티티에서 제거된 자식 엔티티를 자동으로 삭제하는 방식,, 부모엔티티에서 연관이 끊긴 자식엔티티는 DB에서도 삭제
// @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
// private List<CartItem> cartItems = new ArrayList<>();
//    private int quantity; // 수량
