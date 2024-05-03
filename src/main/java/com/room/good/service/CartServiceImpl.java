package com.room.good.service;

import com.room.good.dto.CartDTO;
<<<<<<< HEAD
import com.room.good.dto.CartItemDTO;
import com.room.good.entity.Cart;
import com.room.good.entity.CartItem;
import com.room.good.entity.ClubMember;
import com.room.good.entity.Product;
import com.room.good.repository.CartItemRepository;
import com.room.good.repository.CartRepository;
import com.room.good.repository.ClubMemberRepository;
import com.room.good.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Log4j2
@Service
@RequiredArgsConstructor
=======
import org.springframework.stereotype.Service;

@Service
>>>>>>> origin/jong
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ClubMemberRepository clubMemberRepository;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public Long addCartList(CartItemDTO cartItemDTO, String clubMemberemail) {
        // 카트 만들기
        // addCartList함수를 써서 CartItemDTO과 clubMemberId에다가 값을 넣어줄거다
        Optional<ClubMember> byClubMemberId = clubMemberRepository.findByEmail2(clubMemberemail);
        // Optional은 ClubMember라는걸 레포지터리로 정보를 가져와서 검은 봉지로 한번더 포장한 느낌 ,,,
        ClubMember clubMember = new ClubMember();
        clubMember = byClubMemberId.get(); // 그리고 여기서 그 포장지를 벗겨서 clubMember에 가져온 정보를 넣어주는 느낌
        Cart cart = cartRepository.findByClubMemberId(clubMember.getId()); // 클럽멤버아이디 찾아서 cart에 넣어줌
        log.info(cart);
        log.info("======================================================================================================");
        if (cart == null) { // 기존에 있는 cart가 있고 없으면 새로운 cart 만들어서 줌
            cart = Cart.createCart(clubMember);
        }
        // 상품 담기
        CartItem cartItem = cartItemRepository.findByCartIdAndCartItemId(cart.getCno(), cartItemDTO.getPno());
        // 아이템id랑 카트id를 카트아이템레포지터리로 찾아서 정보를 cartItem에 넣어준다
        log.info("============================================================================");
        log.info(cartItemDTO.getPno());
        List<Object[]> productList = productRepository.getProduct(cartItemDTO.getPno());

        if (cartItem != null) { // 찾은 카트아이템이 null이 아닐때
            cartItem.addQuantity(cartItemDTO.getQuantity()); // 아이템 갯수를 추가한다
            return cartItem.getId(); // 장바구니 상품의 id를 반환함
        } else { // 새로운 카트 아이템을 만들어줘야함,,cartItem정보에다가 가져온 정보를 넣어줘야하는데 생각중
            cartItem = CartItem.createCartItem(cart, (Product) productList, cartItemDTO.getQuantity());
        } // cartItem에 createCartItem 메서드를 호출해서 불러온다
        return cartItem.getId();
    }

    // 장바구니 목록 조회
    @Override
    @Transactional
    public List<CartDTO> getCartList(String email) {
        // 배열 생성
        List<CartDTO> cartDTOList = new ArrayList<>();

        // 장바구니목록 가져오기
        Optional<ClubMember> clubMemberOptional = clubMemberRepository.findByEmail2(email); //email로 회원 찾기
        if (clubMemberOptional.isPresent()) { // 현재
            ClubMember clubMember = clubMemberOptional.get();
            Cart cart = cartRepository.findByClubMemberId(clubMember.getId());
            // 장바구니 관련 작업 수행
            if (cart == null) {
                return cartDTOList;
            }
        }
        return null;
    }

    // 장바구니에 있는 상품의 수량 업데이트
    @Override
    @Transactional
    public void updateCartItemCount(Long pno, int quantity) {
        CartItem cartItem = cartItemRepository.findById(pno)
                .orElseThrow(EntityNotFoundException::new); // 객체 비어있을때 예외를 발생시키는 메서드
        cartItem.updateQuantity(quantity);
    }

    // 장바구니 상품 삭제
    @Override
    @Transactional
    public void deleteCartItem(Long pno) {
        CartItem cartItem = cartItemRepository.findById(pno)
                .orElseThrow(EntityNotFoundException::new);
        cartItemRepository.delete(cartItem);
    }



}
