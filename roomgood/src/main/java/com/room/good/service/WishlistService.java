package com.room.good.service;

import com.room.good.dto.WishListDTO;
import com.room.good.entity.WishList;

import java.util.List;

public interface WishlistService {

    boolean addwish(String email, Long pno);

    WishListDTO getlist(Long id);

}
