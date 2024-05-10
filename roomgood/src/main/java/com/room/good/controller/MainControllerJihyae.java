package com.room.good.controller;

import com.room.good.dto.FaqDTO;
import com.room.good.entity.FAQ;
import com.room.good.service.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public String getFAQ(Model model, @PageableDefault(page = 0, size = 6, sort = "faqno", direction = Sort.Direction.DESC)
    Pageable pageable, @RequestParam(name = "searchValue", required = false, defaultValue = "") String searchValue) {

        Page<FAQ> list = null;
        if(searchValue.isEmpty()){
            list = faqService.getAllFAQs(pageable); //기존의 리스트보여줌
        }else{
            list = faqService.findByFaqtitleContaining(searchValue, pageable); //검색리스트반환
        }

        int nowPage = list.getPageable().getPageNumber() + 1; //pageable에서 넘어온 현재페이지를 가지고올수있다 * 0부터시작하니까 +1
        int startPage = Math.max(nowPage - 4, 1); //매개변수로 들어온 두 값을 비교해서 큰값을 반환
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list" , list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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


    @GetMapping("/FAQ-detail/{faqno}")
    public String getFAQDetail(@PathVariable Long faqno, Model model) {

        Optional<FAQ> optionalFaq = faqService.getFAQById(faqno);
        FAQ faq = optionalFaq.orElse(null);

        model.addAttribute("faq", faq);

        return "FAQ-detail";
    }


    @GetMapping("/FAQ-modify/{faqno}")
    public String modifyFAQ(@PathVariable Long faqno, Model model) {
        Optional<FAQ> optionalFaq = faqService.getFAQById(faqno);
        FAQ faq = optionalFaq.orElse(null);
        System.out.println("faq!!"+faq);
        model.addAttribute("faq", faq);
        return "FAQ-modify";
    }

    @PostMapping("/FAQ-update")
    public String updateFAQ(@ModelAttribute FAQ faq) {
        System.out.println("faq!!!!!!!!!!!"+faq);
        faqService.updateFAQ(faq);
        return "redirect:/FAQ-detail/" + faq.getFaqno();
    }

    @GetMapping("/FAQ-delete/{faqno}")
    public String deleteFAQ(@PathVariable Long faqno, Model model) {
        faqService.deleteFAQ(faqno);
        return "redirect:/FAQ";
    }


}
