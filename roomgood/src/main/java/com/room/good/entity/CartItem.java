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
@ToString(exclude = {"cart"})
@Table(name = "cart_item")
public class CartItem extends BaseEntity {
    // 카트아이템에 필요한건 상품고유번호, 카트고유번호, 상품정보(), 갯수, 카트아이템을 넣을 생성자, 카운트
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_item_id") // 아이템고유번호
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "cart_id")
    private Cart cart; // 카트 고유번호

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int quantity;

    public static CartItem createCartItem(Cart cart, Product product, int quantity) { // 물품 하나의 정보
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        return cartItem;
    }

    public void addQuantity(int quantity){ // 수량을 추가하는것
        this.quantity += quantity;
    }
    public void updateQuantity(int quantity){ // 수량을 업데이트하는것
        this.quantity = quantity;
    }
}

