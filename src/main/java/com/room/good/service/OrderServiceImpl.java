//package com.room.good.service;
//
//import com.room.good.dto.OrderDTO;
//import com.room.good.entity.ClubMember;
//import com.room.good.entity.Order1;
//import com.room.good.entity.OrderItem;
//import com.room.good.entity.Product;
//import com.room.good.repository.ClubMemberRepository;
//import com.room.good.repository.OrderRepository;
//import com.room.good.repository.ProductRepository;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Log4j2
//@Service
//@RequiredArgsConstructor
//public class OrderServiceImpl implements OrderService{
//    private final ProductRepository productRepository;
//    private final ClubMemberRepository clubMemberRepository;
//    private final OrderRepository orderRepository;
//
//
//    @Override
//    public Long order(OrderDTO orderDTO, String email) {
//        Product product = productRepository.findById(orderDTO.getProductId()) // 상품찾는 쿼리문으로 상품 찾아서 변수 넣어줌
//                .orElseThrow(EntityNotFoundException::new);
//        Optional<ClubMember> byEmail2 = clubMemberRepository.findByEmail2(email);
//        ClubMember clubMember = new ClubMember(); // 현재 회원 찾아서 넣어준다
//        clubMember = byEmail2.get();
//
//        List<OrderItem> orderItemList = new ArrayList<>(); // 담은 상품들 여러개일수 있으니까 리스트형식
//        OrderItem orderItem = OrderItem.createOrderItem(product, orderDTO.getCount()); // 아이템에 갯수랑 정보 넣어줌
//        orderItemList.add(orderItem); // orderItem에 넣은걸 배열에 다시 또 넣어준다
//
//        Order1 order1 = Order1.createOrder(clubMember, orderItemList);
//        //그리고 만든 배열에 들어가있는 정보들과 현재 회원 찾은걸 createOrder메서드를 이용해 세팅해주고 order1에 넣어준다
//        orderRepository.save(order1);
//
//        return order1.getOno();
//    }
//}