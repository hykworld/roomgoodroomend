package com.room.good.controller;


import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.PageResultDTO;
import com.room.good.dto.ProductDTO;
import com.room.good.dto.RatingSummaryDTO;
import com.room.good.entity.CategoryBig;
import com.room.good.entity.Product;
import com.room.good.service.MemberService;
import com.room.good.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainControllerHwang {
    private final ProductService productService;


    @GetMapping("/shop")
    public String readshop(PageRequestDTO pageRequestDTO, Model model,
                           @RequestParam(required = false) Long cno,
                           @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) {

        model.addAttribute("productCount", productService.getProductCount());
        System.out.println("aaaaa"+productService.getProductCount());

        if(keyword.isEmpty()){
            if (cno != null) {
                model.addAttribute("result", productService.categoryPage(cno, pageRequestDTO));
            } else {
                model.addAttribute("result", productService.getList(pageRequestDTO));
            }
        }else{
            model.addAttribute("result", productService.findByPnameContaining(keyword, pageRequestDTO)); //검색리스트반환
        }
        return "shop"; // shop.html로 이동

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/productregister")
    public void register(){
    };

    @PostMapping("/productregister")
    public String itemregister(ProductDTO productDTO, RedirectAttributes redirectAttributes){

        log.info("{} productDTOproductDTOproductDTO", productDTO.getCategoryBig());

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

    public void productregisterget(){}

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/productmodify")
    public String productmodify(ProductDTO productDTO, PageRequestDTO requestDTO, RedirectAttributes redirectAttributes){
        Long pno=productService.modify(productDTO);
        System.out.println("ssssss"+productDTO);
        redirectAttributes.addFlashAttribute("msg",pno);
        redirectAttributes.addAttribute("page",requestDTO.getPage());
        return "redirect:/shop";
    };
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/productremove")
    public String remove(long pno, RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO, Model model){
        productService.remove(pno);
        redirectAttributes.addFlashAttribute("msg",pno);
        model.addAttribute("result",productService.getList(pageRequestDTO));
        return "redirect:/shop";
    }
}
