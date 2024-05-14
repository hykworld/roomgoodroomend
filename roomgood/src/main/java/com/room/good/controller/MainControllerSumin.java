package com.room.good.controller;

import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.ProductDTO;
import com.room.good.service.ProductService;
import com.room.good.service.SoominService;
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

    @GetMapping({"/","/main","/index"})
    public String mainPage(Model model){

        model.addAttribute("time",soominService.getTimeSaleList());

        return "/index";
    };

}
