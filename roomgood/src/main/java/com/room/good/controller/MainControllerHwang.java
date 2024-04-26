package com.room.good.controller;

import com.room.good.dto.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainControllerHwang {

    @GetMapping("/productregister")
    public void register(){
    };

    @GetMapping("/productmodify")
    public void modify(){
    };

    public void productregisterget(){}


}
