package com.room.good.service;

import com.room.good.dto.CartDTO;
import com.room.good.dto.CartttDTO;

public interface CartttService {


    Boolean additem(String email,Long pno,int count);

    CartttDTO findlist(Long cno);
}
