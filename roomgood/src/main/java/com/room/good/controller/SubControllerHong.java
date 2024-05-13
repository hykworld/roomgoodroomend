package com.room.good.controller;

import com.room.good.dto.CartDTO;
import com.room.good.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Log4j2
@RestController
@RequiredArgsConstructor
public class SubControllerHong {
    private final CartService cartService;

    @DeleteMapping(value = "/cartItem/{cartItemId}")
    public @ResponseBody ResponseEntity<Long> deleteCartItem(@PathVariable("cartItemId") Long cartItemId, Principal principal){
        cartService.deleteCartItem(cartItemId);
        return new ResponseEntity<>(cartItemId, HttpStatus.OK);
    }
//    @PatchMapping(value = "/cartItem/{cartItemId}")
//    public @ResponseBody ResponseEntity<Long> updateCartItem(@PathVariable("cartItemId") Long cartItemId, int quantity, Principal principal){
//        cartService.updateCartItemCount(cartItemId, quantity);
//        return new ResponseEntity<>(cartItemId, HttpStatus.OK);
//    }
}
