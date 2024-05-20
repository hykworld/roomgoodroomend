package com.room.good.controller;

import com.querydsl.core.types.Order;
import com.room.good.dto.*;
import com.room.good.entity.Order1;
import com.room.good.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jmx.export.naming.IdentityNamingStrategy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.model.IModel;

import java.security.Principal;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainControllerKOO {

    private final EventService eventService;
    private final MemberService memberService;
    private final OrderrrService orderrrService;
    private final CartttService cartttService;
    private  final ProductService productService;
    private  final ProductttService productttService;
    private  final WishlistService wishlistService;






    @GetMapping("/blog")
    public void getblog(PageRequestDTO pageRequestDTO, Model model,HttpSession session ){

        model.addAttribute("result",eventService.getList(pageRequestDTO));
        log.info("resultresult"+eventService.getList(pageRequestDTO));
    };


    @GetMapping("/blog-details")
    public void getBDetails(Long eno, @ModelAttribute PageRequestDTO pageRequestDTO, Model model){

        EventDTO eventDTO = eventService.read(eno);
        model.addAttribute("event",eventDTO);
        model.addAttribute("eno",eno);
        model.addAttribute("preno",eno-1);
        model.addAttribute("nextno",eno+1);
    };

//    @GetMapping("/eventmodify")
//    public void geteventmodify(Long eno, @ModelAttribute PageRequestDTO pageRequestDTO, Model model){
//
//        EventDTO eventDTO = eventService.read(eno);
//        model.addAttribute("event",eventDTO);
//        model.addAttribute("eno",eno);
//        model.addAttribute("preno",eno-1);
//        model.addAttribute("nextno",eno+1);
//    };
//
//    @PostMapping("/eventmodify")
//    public String posteventmodify(EventDTO eventDTO , RedirectAttributes redirectAttributes){
//        log.info("EventDTO: " + eventDTO);
//        Long mno = eventService.register(eventDTO);
//        redirectAttributes.addFlashAttribute("msg", mno+"번 글이 수정되었습니다.");
//        return "redirect:/blog";
//    };


    @GetMapping("/about")
    public void about(PageRequestDTO pageRequestDTO, Model model,@RequestParam(defaultValue = "0") String priceno) {

        model.addAttribute("result", productttService.getListtt(pageRequestDTO,priceno));
        model.addAttribute("priceno",priceno);

    }
    @GetMapping("/contact")
    public void getcontact(){};
    @GetMapping("/main")
    public void getmain(){};

    @GetMapping("/eventRegister")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void eventRegisterGet(){

    };
    @PostMapping("/eventRegister")
    public String eventRegisterPost(EventDTO eventDTO , RedirectAttributes redirectAttributes){
        log.info("EventDTO: " + eventDTO);
        Long mno = eventService.register(eventDTO);
        redirectAttributes.addFlashAttribute("msg", mno+"번 글로 등록되었습니다.");
        return "redirect:/blog";
    };

    @GetMapping("/join")
    public void join(Model model, HttpSession session){
        log.info("session email : " + session.getAttribute("email"));
        log.info("session id : " + session.getAttribute("id"));
        log.info("session " + session);

        String email =(String)session.getAttribute("email");
        Long id = (Long)session.getAttribute("id");
        model.addAttribute("email",email);
        model.addAttribute("id",id);
    }

    @PostMapping("/join")
    public String joinPost(String year,String month,String day,MemberDTO memberDTO, RedirectAttributes redirectAttributes){
        memberDTO.setBirth(year+"-"+month+"-"+day);// 구분해서 넣으려고 나중에 - 이거 기준으로 잘라서 수정할거임
        memberDTO.setGrade("일반회원");
        log.info(memberDTO);

        //그냥 memberDTO memberDTO 써도 됨 근데 이해하기쉽게 이래 서놔씀
        if (memberService.join(memberDTO)){
            redirectAttributes.addFlashAttribute("msg","회원가입에 성공하셨습니다.");
            return "redirect:/index";
        }else {
            redirectAttributes.addFlashAttribute("msg","이미 가입되어있는 핸드폰 번호입니다.");
            return "redirect:/index";
        }

    };
    @GetMapping("/memberJoin")
    public void memberJoin() {
    }

    @PostMapping("/memberJoin")
    public String memberJoin(RedirectAttributes redirectAttributes, MemberDTO memberDTO, HttpSession session ,String year,String month,String day) {
        memberDTO.setBirth(year+"-"+month+"-"+day);// 구분해서 넣으려고 나중에 - 이거 기준으로 잘라서 수정할거임
        memberDTO.setGrade("일반회원");
        log.info("memberDTOmemberDTO"+memberDTO);
        if (memberService.check(memberDTO.getEmail())) {
            redirectAttributes.addFlashAttribute("msg", "이미 존재하는 이메일입니다.");
            log.info("memberJoinif");
            return "redirect:/memberJoin";
        }//이메일 겹치면 리턴함
        else {
            log.info(memberDTO + "memberDTOmemberDTOmemberDTO");
            memberService.memberJoin(memberDTO);

            log.info(session);
            log.info("sessionsession" + session.getId());

            redirectAttributes.addFlashAttribute("msg", "회원가입에 성공하셨습니다.");
            return "redirect:/index";
        }

    }
    @GetMapping("/faillogin")
    public String loginmovie(String error,RedirectAttributes redirectAttributes){
        //로그인 실패했을 떄 메세지 띄워주는데 그 정보들을 url에 안보여주고싶어서 컨트롤러 하나 거쳐서 가는중

        log.info(error+"errrerererer");
        redirectAttributes.addFlashAttribute("msg",error);
        return "redirect:/Loginshop";
    };


    @GetMapping("/mypage")
    public void getmypage(Model model, Principal principal,@RequestParam(defaultValue = "member")String category){

        String email = principal.getName();
        MemberDTO memberDTO = memberService.findbyid(email);
        log.info("memberDTOmemberDTO"+memberDTO);
        List<OrderDTO> orderDTO = orderrrService.orderlist(memberDTO.getId());
        int count1 =0;
        int count2 =0;
        int count3 =0;
        int count4 =0;
        for(int i = 0 ; i<orderDTO.size();i++){
            Long count = (long) orderDTO.get(i).getOrderItemDTO().size();
            orderDTO.get(i).setCount(count);
            if(orderDTO.get(i).getStatus().equals("결제완료")){
                count1++;
            }else if(orderDTO.get(i).getStatus().equals("배송준비중")){
                count2++;
            }else if(orderDTO.get(i).getStatus().equals("배송중")){
                count3++;
            }else if(orderDTO.get(i).getStatus().equals("배송완료")){
                count4++;
            }
        }

        log.info("orderlistorderlist"+orderDTO);
        model.addAttribute("orderlist",orderDTO);
        model.addAttribute("count",orderDTO.size());
        model.addAttribute("count1",count1);
        model.addAttribute("count2",count2);
        model.addAttribute("count3",count3);
        model.addAttribute("count4",count4);
        model.addAttribute("memberDTO",memberDTO);

        model.addAttribute("category",category);
    };
    @PostMapping("/mypage")
    public String postmypage(){

        return "redirect:/mypageupdate";
    };

    @GetMapping("/mypageupdate")
    public void getmypageupdate(Model model, Principal principal){

        String email = principal.getName();
        MemberDTO memberDTO = memberService.findbyid(email);
        String[] splitBirth = memberDTO.getBirth().split("-");
        log.info(splitBirth+"splitBirthsplitBirth");
        model.addAttribute("memberDTO",memberDTO);
        model.addAttribute("birth1",splitBirth[0]);
        model.addAttribute("birth2",splitBirth[1]);
        model.addAttribute("birth3",splitBirth[2]);
    };
    @PostMapping("/mypageupdate")
    public String postmypageupdate(MemberDTO memberDTO,RedirectAttributes redirectAttributes,String year,String month,String day){
        memberDTO.setBirth(year+"-"+month+"-"+day);
        log.info("memberDTOmypageupdate"+memberDTO);
        memberService.membermodify(memberDTO);
        redirectAttributes.addFlashAttribute("msg","회원 정보 수정을 완료했습니다.");
        return "redirect:/mypage";
    };
    @GetMapping("/Loginshop")
    public void getLoginshop(){};
    @GetMapping("/Logout")
    public String getLogout(HttpSession session){
        session.invalidate();

        return "redirect:/index";
    };

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/wishlist")
    public void wishlist(Model model, Principal principal){
        String email = principal.getName();
        MemberDTO memberDTO = memberService.findbyid(email);
        Long id = memberDTO.getId();

        if(wishlistService.findwish(id)){
            WishListDTO getlist = wishlistService.getlist(id);
            model.addAttribute("getlist",getlist);
            model.addAttribute("memberDTO",memberDTO);
        }else{
            model.addAttribute("getlist","0");
            log.info("memberDTOmemberDTO"+memberDTO);
            model.addAttribute("memberDTO",memberDTO);
        };


    };

    @GetMapping("/resetpw")
    public void getCheckout(){};
    @GetMapping("/eventdelete")
    public String geteventdelete(Long eno,RedirectAttributes redirectAttributes){

        eventService.delete(eno);
        redirectAttributes.addFlashAttribute("msg","글이 삭제되었습니다.");
        return "redirect:/blog";
    };

    @GetMapping("/ajaxtestpage")
    public void getajaxtestpage(){};
    @GetMapping("/payfinish")
    public void getpayfinish(){};



    @GetMapping("/pay")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void getpay(Principal principal,Model model){
        String email = principal.getName();
        MemberDTO memberDTO = memberService.findbyid(email);
        CartttDTO findlist = cartttService.findlist(memberDTO.getCartnumber());
        log.info("findlist"+findlist);
        log.info("findlist"+findlist.getCartItems().get(0).getProduct());//잘넘어온다~
        int paymoney =0;
        int discount =0;
        int fee = 0;
        for (int i =0; i<findlist.getCartItems().size();i++){
            paymoney+= (int) (findlist.getCartItems().get(i).getProduct().getOriginalPrice()*findlist.getCartItems().get(i).getQuantity());
            discount+= (int) (findlist.getCartItems().get(i).getProduct().getPrice()*findlist.getCartItems().get(i).getQuantity());
            if(findlist.getCartItems().get(i).getProduct().getCategoryBig().getCno()==1L || findlist.getCartItems().get(i).getProduct().getCategoryBig().getCno()==2L){
                fee += 5000;
            }
        }
        String pipath= findlist.getCartItems().get(0).getProduct().getImages().get(0).getPipath();
        String piuuid=findlist.getCartItems().get(0).getProduct().getImages().get(0).getPiuuid();
        String piimgName= findlist.getCartItems().get(0).getProduct().getImages().get(0).getPiimgName();
        String url= pipath+"/"+piuuid+"_"+piimgName;
        int dicountmoney = paymoney-discount;
        log.info(paymoney+"paymoneypaymoney");
        log.info(discount+"discountdiscount");

        model.addAttribute("findlist",findlist.getCartItems());
        model.addAttribute("paymoney",paymoney);
        model.addAttribute("discount",discount);
        model.addAttribute("dicountmoney",dicountmoney);
        model.addAttribute("ordercount",findlist.getCartItems().size());
        model.addAttribute("url",url);
        model.addAttribute("fee",fee);
    };


    @GetMapping("adminpage")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void adminpageget(Model model){

        List<OrderDTO> orderlistall = orderrrService.orderlistall();
        for(int i = 0 ; i<orderlistall.size();i++){
            Long count = (long) orderlistall.get(i).getOrderItemDTO().size();
            orderlistall.get(i).setCount(count);
        }
        log.info(orderlistall+"orderlistallorderlistall");
        model.addAttribute("orderlistall",orderlistall);

    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("passreadyproduct")
    public String passreadyproduct(Long ono){
        orderrrService.modifystatus(ono);

        return "redirect:/adminpage";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("passreadyproduct2")
    public String passreadyproduct2(Long ono){
        orderrrService.modifystatus2(ono);
        return "redirect:/adminpage";
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("passreadyproduct3")
    public String passreadyproduct3(Long ono){
        orderrrService.modifystatus3(ono);
        return "redirect:/adminpage";
    }


}
