package com.room.good.repository;

import com.room.good.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartttRepository extends JpaRepository<Cart,Long> {



    //Cart findByClubMemberId(Long id);


}
