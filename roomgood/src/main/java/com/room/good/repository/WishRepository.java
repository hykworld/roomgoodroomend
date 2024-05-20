package com.room.good.repository;

import com.room.good.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<WishList, Long> {


    List<WishList> findByClubMemberId(Long clubmemberid);
    void deleteAllByProductPno(Long pno);
}
