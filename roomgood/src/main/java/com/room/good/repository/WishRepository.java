package com.room.good.repository;

import com.room.good.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<WishList, Long> {



}
