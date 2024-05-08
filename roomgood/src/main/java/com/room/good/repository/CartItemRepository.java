//package com.room.good.repository;
//
//import com.room.good.entity.CartItem;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//
//import java.util.List;
//
//public interface CartItemRepository extends JpaRepository<CartItem, Long> {
////  CartItem findByCartIdAndCartItemId(Long cartId, Long cartItemId);
//
//    // 쿼리메소드 이용할때 엔티티 이름 생략 가능 함
//    // find + 엔티티 이름 + by + 변수이름
//    @Query ("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.id = :cartItemId")
//    // select cart
//    //
//    CartItem findByCartIdAndCartItemId(@Param("cartId") Long cartId, @Param("cartItemId") Long cartItemId);
//
//
//    @Query ("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId")
//    List<CartItem> findByCartId(@Param("cartId") Long cartId);
//
//}
/////////////////////////////////////////////////////////////////////////////////////////////////////////
package com.room.good.repository;

import com.room.good.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
//  CartItem findByCartIdAndCartItemId(Long cartId, Long cartItemId);

    // 쿼리메소드 이용할때 엔티티 이름 생략 가능 함
    // find + 엔티티 이름 + by + 변수이름
    @Query ("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.id = :cartItemId")
    // select cart
    //
    CartItem findByCartIdAndCartItemId(@Param("cartId") Long cartId, @Param("cartItemId") Long cartItemId);

    @Query ("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId")
    List<CartItem> findByCartId(@Param("cartId") Long cartId);

//    CartItem findByCartIdAndProductId(Long cartCno, Long productId);
}
