package com.room.good.controller;


import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.ProductDTO;
import com.room.good.service.MemberService;
import com.room.good.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainControllerHwang {
    private final ProductService productService;



    /*shop = 리스트 페이지 =======================================================*/
    @GetMapping("/shop")
    public void readshop(PageRequestDTO pageRequestDTO, Model model ){
        model.addAttribute("result",productService.getList(pageRequestDTO));

        log.info("resultresult"+productService.getList(pageRequestDTO));
    };

<<<<<<< HEAD
    /*상품 등록***********************************/
=======
/*상품 등록***********************************/
>>>>>>> 8670f751e87aaa765f280cc5004f10385d44571f
    @GetMapping("/productregister")
    public void register(){
    };

    @PostMapping("/productregister")
    public String itemregister(ProductDTO productDTO, RedirectAttributes redirectAttributes){

        Long pno=productService.register(productDTO);
        redirectAttributes.addFlashAttribute("msg",pno);

        System.out.println("msg"+pno);
        return "redirect:/shop";
    };
    /*상품 수정***********************************/
    @GetMapping({"/productread","/productmodify"})
    public void modify(Long pno,  @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO, Model model, RedirectAttributes redirectAttributes ){
        ProductDTO productDTO=productService.read(pno);

        model.addAttribute("result",productService.getList(pageRequestDTO));
        model.addAttribute("dto",productDTO);
        // @ModelAttribute("requestDTO")
    };

<<<<<<< HEAD
    @PostMapping("/productmodify")
    public String productmodify(ProductDTO productDTO, PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        Long pno=productService.modify(productDTO);

        redirectAttributes.addFlashAttribute("msg",pno);
        redirectAttributes.addAttribute("page",requestDTO.getPage());
        return "redirect:/shop";
    };

    @PostMapping("/productremove")
    public String remove(long pno, RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO, Model model){
        productService.remove(pno);
        redirectAttributes.addFlashAttribute("msg",pno);
        model.addAttribute("result",productService.getList(pageRequestDTO));
        return "redirect:/shop";
    }
=======
>>>>>>> 8670f751e87aaa765f280cc5004f10385d44571f


    @PostMapping("/productmodify")
    public String productmodify(ProductDTO productDTO, PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        Long pno=productService.modify(productDTO);

        redirectAttributes.addFlashAttribute("msg",pno);
        redirectAttributes.addAttribute("page",requestDTO.getPage());
        return "redirect:/shop";
    };

    @PostMapping("/productremove")
    public String remove(long pno, RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO, Model model){
        productService.remove(pno);
        redirectAttributes.addFlashAttribute("msg",pno);
        model.addAttribute("result",productService.getList(pageRequestDTO));
        return "redirect:/shop";
    }



}
