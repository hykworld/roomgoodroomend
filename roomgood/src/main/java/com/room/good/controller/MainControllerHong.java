package com.room.good.controller;

import com.room.good.dto.CartDTO;
import com.room.good.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainControllerHong {
    private final CartService cartService;
    @GetMapping("/shopping-cart")
    public void shoppingCart(CartDTO cartDTO, Model model){
        model.addAttribute("result",cartService.cartList(cartDTO, cartDTO.getClubMemberId()));
    }


}

