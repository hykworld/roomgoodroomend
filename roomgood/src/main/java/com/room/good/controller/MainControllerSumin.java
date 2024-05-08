package com.room.good.controller;

import com.room.good.dto.PageRequestDTO;
import com.room.good.service.SoominService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainControllerSumin {

    private final SoominService soominService;

    @GetMapping({"/","/main","/index"})
    public String mainPage(Model model, PageRequestDTO pageRequestDTO){

        model.addAttribute("time",soominService.getTimeSaleList());

        return "/index";
    };

    @GetMapping("/shop-details")
    public void getSDetails(Model model){



    };
}
