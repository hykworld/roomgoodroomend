package com.room.good.controller;

import com.room.good.dto.CartDTO;
import com.room.good.dto.CartItemDTO;
import com.room.good.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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
        List<CartDTO> cartDTOList = cartService.getCartList(principal.getName());
        log.info(principal.getName());
        log.info("=======================================================================");
        model.addAttribute("cartItems" , cartDTOList);
        return "shopping-cart";
    }

    // 장바구니 담기
    @PostMapping(value = "/add")
    public @ResponseBody ResponseEntity<?> addCartProduct(@RequestBody CartItemDTO cartItemDTO, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            for(FieldError fieldError : fieldErrors){
                sb.append(fieldError.getDefaultMessage());
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        String email = principal.getName();
        Long cno;

        try{
            cno = cartService.addCartList(cartItemDTO, email);
        }catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Long>(cno, HttpStatus.OK);
    }


    // orderController에 들어갈거
    @PostMapping(value = "/shopping-cart")
    public @ResponseBody ResponseEntity order(@RequestBody @Validated CartItemDTO cartItemDTO, BindingResult bindingResult, Principal principal){
        // @RequestBody는 http요청의 본문에 포함된 데이터를 자바객체로 변환
        // @Validated는 유효성검사 수행, 결과를 BindingResult에 저장
        if(bindingResult.hasErrors()){ // bindingResult, StringBuilder는 스프링부트프레임워크에서 제공
            StringBuilder sb = new StringBuilder(); // 에러를 담을 그릇 만들고
            // StringBuilder는 문자열 연결 및 조작 시 발생하는 성능 문제를 해결하는데 씀
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            // 바인딩하면서 생긴 오류를 가져와서 리스트타입의 fieldError객체에 넣어줌
            // FieldError는 객체에 발생한 오류정보를 가지고있음
            for(FieldError fieldError : fieldErrors){
                // 여기서 리스트객체(fieldErrors)에 모든 필드오류가 담겨있으니까 각각 오류정보를 추출하는게 필요함
                // 그래서 for문을 돌려서 다시 하나하나 넣어줌
                sb.append(fieldError.getDefaultMessage());
                // getDefaultMessage는 FieldError에 포함된 기본오류 메세지를 반환함
            }
            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
            // HttpStatus.BAD_REQUEST는 오류메세지를 포함한 HTTP응답을 생성해서 클라이언트한테 반환 400오류임
        }
        return new ResponseEntity<Long>(cartItemDTO.getPno(), HttpStatus.OK);
        // 생성된 장바구니 상품아이디와 요청이 성공했다는 응답상태코드 반환
    }
}

