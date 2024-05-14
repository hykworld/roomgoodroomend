package com.room.good.service;

import com.querydsl.core.types.Order;
import com.room.good.dto.OrderDTO;
import com.room.good.dto.OrderItemDTO;
import com.room.good.entity.*;
import com.room.good.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrderrrServiceImpl implements OrderrrService{

    private final ClubMemberRepository clubMemberRepository;
    private final ProductRepository productRepository;
    private final CartttRepository cartttRepository;
    private final CartttItemRepository cartttItemRepository;
    private final OrderrrRepository orderrrRepository;
    private final OrderrrItemRepository orderrrItemRepository;
    @Override
    @Transactional
    public boolean cartlistpay(String email, String receiver) {

        Optional<ClubMember> byEmail2 = clubMemberRepository.findByEmail2(email);//일단 이메일로 정보찾기
        ClubMember clubMember = byEmail2.get();// 카트번호 찾아야돼서
        log.info(clubMember+"clubMemberclubMember");
        Optional<Cart> byId = cartttRepository.findById(clubMember.getCartnumber());//카트번호로 카트찾기
        Cart cart = byId.get();
        log.info(cart+"cartcart");

        List<CartItem> byCartCno = cartttItemRepository.findByCartCno(cart.getCno());
        log.info(byCartCno+"byCartCnobyCartCno");
        log.info(byCartCno.size()+"byCartCno.size()");


        Order1 order1 = new Order1();
        order1.setClubMember(clubMember);
        order1.setStatus("배송준비중");
        order1.setRecipient(receiver);
        orderrrRepository.save(order1);
        log.info(byCartCno.size()+"byCartCno.size()");
        List<OrderItem> orderItemList = new ArrayList<>();
        long plusprice=0;
        for(int i =0;i<byCartCno.size();i++){
            OrderItem orderItem = new OrderItem();
            orderItem.setCount((long) byCartCno.get(i).getQuantity());
            orderItem.setOrder1(order1);
            orderItem.setItemprice(byCartCno.get(i).getProduct().getPrice());
            orderItem.setItemno(byCartCno.get(i).getProduct().getPno());
            orderItem.setProname(byCartCno.get(i).getProduct().getPname());
            long totalprice = byCartCno.get(i).getProduct().getPrice() * byCartCno.get(i).getQuantity();
            log.info(totalprice+"totalprice");
            orderItem.setTotalitemprice(totalprice);
            plusprice+=totalprice;
            log.info("orderItem : "+i+"//"+orderItem);
            orderrrItemRepository.save(orderItem);
        }
        //마일리지부분
        Long mileage = clubMember.getMileage();
        mileage +=plusprice/100;
        clubMember.setMileage(mileage);
        //마일리지부분.end

        //카트 수량 초기화!! 구매했으니깐
        cart.setQuantity(0);

        order1.setPrice(plusprice);
        orderrrRepository.save(order1);
        log.info("herecome?");


        for(int j=0;j<byCartCno.size();j++){
        cartttItemRepository.deleteById(byCartCno.get(j).getId());
        }

        return true;
    }

    @Override
    @Transactional
    public List<OrderDTO> orderlist(Long id) {
        List<OrderDTO> orderDTOList = new ArrayList<>();

        List<Order1> byClubMemberId = orderrrRepository.findByClubMemberId(id);
        log.info(byClubMemberId+"byClubMemberIdbyClubMemberId");
        for(int i = 0; i<byClubMemberId.size();i++){
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setRegDate(byClubMemberId.get(i).getRegDate().toLocalDate().toString());
            orderDTO.setOno(byClubMemberId.get(i).getOno());
            orderDTO.setPrice(byClubMemberId.get(i).getPrice());
            List<OrderItem> byOrder1Ono = orderrrItemRepository.findByOrder1Ono(byClubMemberId.get(i).getOno());
            List<OrderItemDTO> orderItemDTOs = new ArrayList<>();
            log.info("byOrder1OnobyOrder1Ono"+byOrder1Ono);
            for(int j=0;j<byOrder1Ono.size();j++) {
                OrderItemDTO orderItemDTO = new OrderItemDTO();
                orderItemDTO.setCount(byOrder1Ono.get(j).getCount());
                orderItemDTO.setProname(byOrder1Ono.get(j).getProname());
                orderItemDTO.setOino(byOrder1Ono.get(j).getOino());
                orderItemDTO.setItemno(byOrder1Ono.get(j).getItemno());
                orderItemDTO.setItemprice(byOrder1Ono.get(j).getItemprice());
                orderItemDTO.setTotalitemprice(byOrder1Ono.get(j).getTotalitemprice());
                orderItemDTOs.add(orderItemDTO);
            }
            log.info(orderItemDTOs+"orderItemDTOsorderItemDTOs");
            orderDTO.setOrderItemDTO(orderItemDTOs);
            orderDTOList.add(orderDTO);
            log.info(orderDTO+"orderDTOorderDTO");
            log.info(orderDTOList+"infororderDTOList");
        }

        log.info(orderDTOList+"orderDTOListorderDTOListorderDTOList");
        return orderDTOList;
    }
}
