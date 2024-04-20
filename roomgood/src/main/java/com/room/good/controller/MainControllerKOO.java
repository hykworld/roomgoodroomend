package com.room.good.controller;

import com.room.good.dto.EventDTO;
import com.room.good.dto.MemberDTO;
import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.PageResultDTO;
import com.room.good.service.EventService;
import com.room.good.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainControllerKOO {

    private final EventService eventService;
    private final MemberService memberService;
    @GetMapping("/index")
    public void getIndex(){};





    @GetMapping("/blog")
    public void getblog(PageRequestDTO pageRequestDTO, Model model ){
        model.addAttribute("result",eventService.getList(pageRequestDTO));
    };


    @GetMapping("/blog-details")
    public void getBDetails(Long eno, @ModelAttribute PageRequestDTO pageRequestDTO, Model model){

        EventDTO eventDTO = eventService.read(eno);
        model.addAttribute("event",eventDTO);
    };
    @GetMapping("/about")
    public void getabout(){};
    @GetMapping("/contact")
    public void getcontact(){};
    @GetMapping("/main")
    public void getmain(){};


    @GetMapping("/eventRegister")
    public void eventRegisterGet(){

    };
    @PostMapping("/eventRegister")
    public String eventRegisterPost(EventDTO eventDTO , RedirectAttributes redirectAttributes){
        log.info("movieDTO: " + eventDTO);
        Long mno = eventService.register(eventDTO);
        redirectAttributes.addFlashAttribute("msg", mno+"번 글로 등록되었습니다.");
        return "redirect:/blog";
    };

    @GetMapping("/join")
    public void join(Model model, HttpSession session){
        log.info("session email : " + session.getAttribute("email"));
        log.info("session id : " + session.getAttribute("id"));
        log.info("session " + session);
        String email =(String)session.getAttribute("email");
        Long id = (Long)session.getAttribute("id");
        model.addAttribute("email",email);
        model.addAttribute("id",id);
    }

    @PostMapping("/join")
    public String joinPost(Long id,String email,String name,String phone, String password, RedirectAttributes redirectAttributes){

        log.info("name = "+ name);
        log.info("phone = "+ phone);
        log.info("password = "+ password);
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(id);
        memberDTO.setEmail(email);
        memberDTO.setName(name);
        memberDTO.setPhone(phone);
        memberDTO.setPassword(password);
        //그냥 memberDTO memberDTO 써도 됨 근데 이해하기쉽게 이래 서놔씀
        if (memberService.join(memberDTO)){
            return "redirect:/blog";
        }else {
            redirectAttributes.addFlashAttribute("msg","이미 가입되어있는 핸드폰 번호입니다.");
            return "redirect:/join";
        }

    };
    @GetMapping("/memberJoin")
    public void memberJoin() {
    }

    @PostMapping("/memberJoin")
    public String memberJoin(RedirectAttributes redirectAttributes, MemberDTO memberDTO, HttpSession session ,String year,String month,String day) {
        memberDTO.setBirth(year+month+day);
        memberDTO.setGrade("일반회원");
        log.info("memberDTOmemberDTO"+memberDTO);
        if (memberService.check(memberDTO.getEmail())) {
            redirectAttributes.addFlashAttribute("msg", "이미 존재하는 이메일입니다.");
            log.info("memberJoinif");
            return "redirect:/memberJoin";
        }//이메일 겹치면 리턴함
        else {
            log.info(memberDTO + "memberDTOmemberDTOmemberDTO");
            memberService.memberJoin(memberDTO);

            log.info(session);
            log.info("sessionsession" + session.getId());

            redirectAttributes.addFlashAttribute("msg", "회원가입에 성공하셨습니다.");
            return "redirect:/blog";
        }
    }
    @GetMapping("/faillogin")
    public String loginmovie(String error,RedirectAttributes redirectAttributes){
        //로그인 실패했을 떄 메세지 띄워주는데 그 정보들을 url에 안보여주고싶어서 컨트롤러 하나 거쳐서 가는중

        log.info(error+"errrerererer");
        redirectAttributes.addFlashAttribute("msg",error);
        return "redirect:/Loginshop";
    };


    @GetMapping("/mypage")
    public void getmypage(){};
    @GetMapping("/Loginshop")
    public void getLoginshop(){};
    @GetMapping("/Logout")
    public void getLogout(){};
    @GetMapping("/checkout")
    public void getCheckout(){};
    @GetMapping("/shop")
    public void getshop(){};
    @GetMapping("/shop-details")
    public void getSDetails(){};
    @GetMapping("/shopping-cart")
    public void getShoppingCart(){};
}
