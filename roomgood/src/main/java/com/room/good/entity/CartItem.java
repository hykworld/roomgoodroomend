package com.room.good.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(exclude = {"cart","product"})
public class CartItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cino;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;//프로독트의 프라이머리키다

    @Column(nullable = false)
    private int quantity;

    public CartItem(Cart cart, Product product, int quantity) { // 물품 하나의 정보
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public void addquantity(int quantity){
        this.quantity += quantity;
    }
}

