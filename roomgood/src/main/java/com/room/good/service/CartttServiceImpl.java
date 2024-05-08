package com.room.good.service;


import com.room.good.entity.Cart;
import com.room.good.entity.CartItem;
import com.room.good.entity.ClubMember;
import com.room.good.entity.Product;
import com.room.good.repository.CartttItemRepository;
import com.room.good.repository.CartttRepository;
import com.room.good.repository.ClubMemberRepository;
import com.room.good.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CartttServiceImpl implements CartttService{

    private final ClubMemberRepository clubMemberRepository;
    private final ProductRepository productRepository;
    private final CartttRepository cartttRepository;
    private final CartttItemRepository cartttItemRepository;


    @Override
    @Transactional
    public Boolean additem(String email, Long pno,int count) {

        Optional<ClubMember> byEmail2 = clubMemberRepository.findByEmail2(email);
        ClubMember clubMember = byEmail2.get();

        log.info(clubMember+"clubMemberclubMember");

        Optional<Product> byId = productRepository.findById(pno);
        Product product = byId.get();

        log.info(product+"productproduct");

        Optional<Cart> byId1 = cartttRepository.findById(clubMember.getCartnumber());
        Cart cart = byId1.get();

        log.info(cart+"cartcart");

        cart.setQuantity(cart.getQuantity()+count);
        //////////////////////////////////////////////////////////////////
        // 이렇게 해도 되고
        CartItem cartItem = new CartItem(cart,product,count);
        log.info(cartItem+"cartItemcartItem");
        //이렇게해도 되고
        cartItem.setCart(cart);// 넣은 카트 번호
        cartItem.setProduct(product);
        cartItem.setQuantity(count);
        //////////////////////////////////////////////////////////////////
        log.info("serviceimpl_cartItem : "+cartItem);
        Optional<CartItem> byProductPnoAndCno = cartttItemRepository.findByProductPnoAndCartCno(pno, clubMember.getCartnumber());
        if(byProductPnoAndCno.isPresent()){
            CartItem cartItem1 = byProductPnoAndCno.get();
            cartItem1.setQuantity(cartItem1.getQuantity()+count);
            log.info("ifififif"+byProductPnoAndCno);
            cartttItemRepository.save(byProductPnoAndCno.get());
        }else {
            log.info("elseelseelseelseelseelse");
            cartttItemRepository.save(cartItem);
        }



        return true;
    }
}
