package com.room.good.controller;

import com.room.good.dto.FaqDTO;
import com.room.good.entity.FAQ;
import com.room.good.service.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainControllerJihyae {

    private final FaqService faqService;

    @GetMapping("/FAQ")
    public String getFAQ(Model model) {

        List<FAQ> faqList = faqService.getAllFAQs();
        System.out.println(">>>>>>>list "+faqList);
        model.addAttribute("faqList", faqList);
        return "faq";
    }

    @GetMapping("/FAQ-register")
    public String getFAQRegister() {

        return "FAQ-register";
    }


    @PostMapping("/faqSave")
    public String faqSave(FaqDTO faqDTO) {
        FAQ faq = faqDTO.toEntity();
        faqService.save(faq);
        return "redirect:/FAQ";
    }

//    @GetMapping("/FAQ-modify/{faqno}")
//    public String getFAQModify() {
//        return "faq-modify";
//    }

    @GetMapping("/FAQ-detail/{faqno}")
    public String getFAQDetail(@PathVariable Long faqno, Model model) {

        Optional<FAQ> optionalFaq = faqService.getFAQById(faqno);
        FAQ faq = optionalFaq.orElse(null);

        model.addAttribute("faq", faq);

        return "FAQ-detail";
    }

    public String goToModifyPage() {
        return "FAQ-modify";
    }

    @GetMapping("/FAQ-modify/{faqno}")
    public String modifyFAQ(@PathVariable Long faqno, Model model) {
        Optional<FAQ> optionalFaq = faqService.getFAQById(faqno);
        FAQ faq = optionalFaq.orElse(null);
        model.addAttribute("faq", faq);
        return "FAQ-modify";
    }
    }
