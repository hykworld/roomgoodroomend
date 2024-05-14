package com.room.good.controller;

import com.room.good.dto.CartDTO;
import com.room.good.dto.PageRequestDTO;
import com.room.good.service.CartService;
import com.room.good.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainControllerHong {
    private final CartService cartService;
    private final ProductService productService;

    // 장바구니 목록
    @GetMapping(value = "/shopping-cart")
    public String shoppingCart(Principal principal, Model model, PageRequestDTO pageRequestDTO) {
        CartDTO cartDTO = cartService.getCartList(principal.getName());
        if (principal != null) { // 로그인했을때 // principal이 있을때
            log.info("컨트롤러 " + principal.getName());
            // cartDTO.getCartItems().get(0).getProduct().getPname();
            log.info("=================ausdhfoasdhflhalsdf======================================================");
            //        String url =  cartDTO.getCartItems().get(0).getProduct().getImages().get(0).getPipath()+"/s_"+ cartDTO.getCartItems().get(0).getProduct().getImages().get(0).getPiuuid()+"_" +cartDTO.getCartItems().get(0).getProduct().getImages().get(0).getPiimgName();

            List<String> urlList = new ArrayList<>();
            for (int i = 0; i < cartDTO.getCartItems().size(); i++) {
                String imageUrl = cartDTO.getCartItems().get(i).getProduct().getImages().get(0).getPipath() + "/s_" +
                        cartDTO.getCartItems().get(i).getProduct().getImages().get(0).getPiuuid() + "_" +
                        cartDTO.getCartItems().get(i).getProduct().getImages().get(0).getPiimgName();
                log.info(imageUrl + " 잘 들어가고있나???????? imageUrl");
                urlList.add(imageUrl);
            }

            log.info(urlList + "근데 왜 안나오냐고…………………..urlListurlList");
            model.addAttribute("cartItems", cartDTO.getCartItems());
            model.addAttribute("url", urlList);
            model.addAttribute("result", productService.getList(pageRequestDTO));
            System.out.println("productService =====================이것도 잘 나오는가??????????? 나와야 하는데????????????????????????????????????????? " + productService.getList(pageRequestDTO));
            System.out.println("컨트롤러 shoppingcart 아 왜 안나오냐고………. cartDTO.cartItems = " + cartDTO.getCartItems());
            return "shopping-cart";
        } else if (principal == null) { // 로그인 안했을때
            return "Loginshop";
        }
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

//    @PostMapping(value = "/shopping-cart/orders")
//    public @ResponseBody ResponseEntity<OrderDTO> orderItem(@RequestBody OrderItemDTO orderItemDTO, Principal principal){
//        OrderItemDTO orderItemDTOList = orderrrItemRepository.findByOrder1Ono(orderItemDTO.getOino());
//    }


}

