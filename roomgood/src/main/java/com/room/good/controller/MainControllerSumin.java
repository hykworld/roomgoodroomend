package com.room.good.controller;

import com.room.good.dto.MemberDTO;
import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.ProductDTO;
import com.room.good.dto.ReviewCountDTO;
import com.room.good.service.MemberService;
import com.room.good.service.ProductService;
import com.room.good.service.SoominService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainControllerSumin {

    private final SoominService soominService;
    private final ProductService productService;
    private final MemberService memberService;

    @GetMapping({"/","/main","/index"})
    public String mainPage(Model model){

        model.addAttribute("time",soominService.getTimeSaleList());

        return "/index";
    };


    @GetMapping("/shop-details")
    public void getSDetails(Long pno, @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model , HttpSession session ){
        if(session != null){ // 세션 확인
            String email = (String) session.getAttribute("email");
            log.info("aaaaaaaaaaaaaaaazzzzzzzzzzzzzzz" + email);
            if(email != null){ // 이메일 정보가 있다면 로그인된 사용자임
                ProductDTO productDTO = productService.read2(pno);
                Long id = memberService.findbyid(email).getId();
                log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+id);
                ReviewCountDTO reviewCountDTO = productService.reviewCount(pno);
                model.addAttribute("reviews", reviewCountDTO);
                model.addAttribute("result", productService.getList(pageRequestDTO));
                model.addAttribute("dto", productDTO);
                model.addAttribute("memberID", id);
                return;
            }
        }

        // 세션이 없거나 이메일 정보가 없는 경우 (로그인되지 않은 사용자)
        ProductDTO productDTO = productService.read2(pno);
        ReviewCountDTO reviewCountDTO = productService.reviewCount(pno);
        Long id = 0L; // 로그인되지 않은 사용자이므로 기본값으로 설정하거나 다른 처리를 수행할 수 있음

        model.addAttribute("reviews", reviewCountDTO);
        model.addAttribute("result", productService.getList(pageRequestDTO));
        model.addAttribute("dto", productDTO);
        model.addAttribute("memberID", id);
    };


}
