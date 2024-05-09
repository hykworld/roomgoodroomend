package com.room.good.service;

import com.room.good.entity.ClubMember;
import com.room.good.entity.Product;
import com.room.good.entity.WishList;
import com.room.good.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService{

    private final ClubMemberRepository clubMemberRepository;
    private final ProductRepository productRepository;
    private final WishRepository wishRepository;

    @Override
    public boolean addwish(String email, Long pno) {

        Optional<ClubMember> byEmail2 = clubMemberRepository.findByEmail2(email);
        Optional<Product> byId = productRepository.findById(pno);

        ClubMember clubMember = byEmail2.get();
        Product product = byId.get();
        WishList wishList =new WishList();
        wishList.setClubMember(clubMember);
        wishList.setProduct(product);
        wishList.setStatus(true);


        log.info(wishList+"wishListwishList");
        wishRepository.save(wishList);
        return true;
    };
}
