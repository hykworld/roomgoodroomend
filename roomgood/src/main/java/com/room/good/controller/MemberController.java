package com.room.good.controller;


import com.room.good.dto.MemberDTO;
import com.room.good.service.MemberService;
import com.room.good.validation.CustomAnnotationCollection;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j //@Log4j2; 문제 생겨서 지움
@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    @PostMapping("/emails/verification-requests")
    public String sendMessage(@RequestParam("email") @Validated @CustomAnnotationCollection.CustomEmail String email, RedirectAttributes redirectAttributes) {
        log.info(email + "emailemail");
        memberService.sendCodeToEmail(email);
        redirectAttributes.addFlashAttribute("email", email);
        redirectAttributes.addFlashAttribute("msg", "인증번호를 보냈습니다.");
        return "redirect:/resetpw";
    }

    @GetMapping("/emails/verifications")
    public String verificationEmail(String email, String code, RedirectAttributes redirectAttributes) {
        boolean same = memberService.checkCode(email, code);
        if (same) {
            redirectAttributes.addFlashAttribute("msgPw", "비번만 변경하기");
            redirectAttributes.addFlashAttribute("email", email);
            return "redirect:/resetpw";
        }
        redirectAttributes.addFlashAttribute("msg", "코드가 일치하지 않습니다.");
        redirectAttributes.addFlashAttribute("email", email);

        return "redirect:/resetpw";
    }

    @PostMapping("/changePW")
    public String changePwPost(String email, String password, String password2, RedirectAttributes redirectAttributes) {
        log.info("emaoaaa"+email,password,password2);

        if (!password.equals(password2)) {
            redirectAttributes.addFlashAttribute("msgPw", "비번만 변경하기");
            redirectAttributes.addFlashAttribute("msg", "입력한 비밀번호들이 다릅니다.");
            return "redirect:/resetpw";
        }

        else {
            log.info("email"+email);
            log.info("password"+password);
            log.info("password2"+password2);

            boolean a = memberService.passwordChange(email,password);
            log.info("here????");
            log.info(a+"aaaaaaaaaaaaaaaaaaaaaa");
            redirectAttributes.addFlashAttribute("msg", "비밀번호 재설정이 성공했습니다.");
            return "redirect:/blog";
        }


    }

    ;

    @GetMapping("/changePW")
    public String changePwGET(String email, String password, String password2, RedirectAttributes redirectAttributes) {
        if (!password.equals(password2)) {
            redirectAttributes.addFlashAttribute("msgPw", "비번만 변경하기");
            redirectAttributes.addFlashAttribute("msg", "입력한 비밀번호들이 다릅니다.");
            return "redirect:/resetpw";
        } else {
            boolean a = memberService.passwordChange(password, email);
            redirectAttributes.addFlashAttribute("msg", "비밀번호 재설정이 성공했습니다.");


            return "redirect:/blog";
        }
    }
}