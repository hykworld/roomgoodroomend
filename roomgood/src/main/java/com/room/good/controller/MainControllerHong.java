package com.room.good.controller;

import com.room.good.dto.CartDTO;
import com.room.good.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import java.security.Principal;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainControllerHong {
    private final CartService cartService;

    // 장바구니 목록
    @GetMapping(value = "/shopping-cart")
    public String shoppingCart(Principal principal, Model model){
        CartDTO cartDTO = cartService.getCartList(principal.getName());
        log.info("컨트롤러 "+principal.getName());
        log.info("=======================================================================");
        model.addAttribute("cartItems" , cartDTO.getCartItems());
        System.out.println("컨트롤러 shoppingcart   cartDTO.cartItems = " + cartDTO.getCartItems());
        return "shopping-cart";
    }

    // 장바구니 담기
    @PostMapping(value = "/add")
    public @ResponseBody ResponseEntity<?> addCartProduct(@RequestBody CartDTO cartDTO, BindingResult bindingResult, Principal principal){
        System.out.println("컨트롤러 cartItemDTO = " + cartDTO);
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            System.out.println("fieldErrors = " + fieldErrors);

            for(FieldError fieldError : fieldErrors){
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long cno;

        try{
            cno = cartService.addCartList(cartDTO, email);
            System.out.println("컨트롤러 트라이cno = " + cno);
        }catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        System.out.println("컨트롤러 캐치cno = " + cno);
        return new ResponseEntity<Long>(cno, HttpStatus.OK);
    }


}

