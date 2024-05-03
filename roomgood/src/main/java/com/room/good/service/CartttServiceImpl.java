//package com.room.good.service;
//
//
//import com.room.good.entity.ClubMember;
//import com.room.good.entity.Product;
//import com.room.good.repository.CartttRepository;
//import com.room.good.repository.ClubMemberRepository;
//import com.room.good.repository.ProductRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@Log4j2
//@RequiredArgsConstructor
//public class CartttServiceImpl implements CartttService{
//
//    private final ClubMemberRepository clubMemberRepository;
//    private final ProductRepository productRepository;
//    private final CartttRepository cartttRepository;
//
//
//    @Override
//    @Transactional
//    public Boolean additem(String email, Long pno) {
//
//        Optional<ClubMember> byEmail2 = clubMemberRepository.findByEmail2(email);
//        ClubMember clubMember = byEmail2.get();
//
//        Optional<Product> byId = productRepository.findById(pno);
//        Product product = byId.get();
//        cartttRepository.FindByClubMemberId(clubMember.getId());// 이러면 카트 번호를 가져올거임
//
//        return true;
//    }
//}
