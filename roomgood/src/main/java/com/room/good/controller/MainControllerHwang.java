package com.room.good.controller;

import com.room.good.dto.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
import org.springframework.ui.Model;
=======
>>>>>>> 69fc336d5aebd640ad29910f36299ef42d919072
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainControllerHwang {

    @GetMapping("/productregister")
<<<<<<< HEAD
    public void register(){
    };

    @GetMapping("/productmodify")
    public void modify(){
    };
=======
    public void productregisterget(){}


>>>>>>> 69fc336d5aebd640ad29910f36299ef42d919072
}
