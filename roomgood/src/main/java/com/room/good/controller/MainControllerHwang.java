package com.room.good.controller;


import com.room.good.dto.ProductDTO;
import com.room.good.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainControllerHwang {
    private final ProductService productService;

    @GetMapping("/productregister")
    public void register(){
    };

    @PostMapping("/productregister")
    public String itemregister(ProductDTO productDTO, RedirectAttributes redirectAttributes){
        System.out.println("msgmsgmsg");
        Long pno=productService.register(productDTO);
        redirectAttributes.addFlashAttribute("msg",pno);

        System.out.println("msg"+pno);
        return "redirect:/shop";
    };

    @GetMapping("/productmodify")
    public void modify(){
    };
<<<<<<< HEAD
=======


>>>>>>> 683fd26678d5750473235e70d98ee4cc3f99c261
    public void productregisterget(){}
    

}
