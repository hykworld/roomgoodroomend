package com.room.good.service;

import com.room.good.dto.OrderDTO;
import com.room.good.entity.Order1;
import com.room.good.entity.OrderItem;

import java.util.List;

public interface OrderrrService {

    boolean cartlistpay(String email, String receiver, String hopestring, String phonenumber, String finaladdress);

    List<OrderDTO> orderlist (Long id);

    List<OrderDTO> orderlistall();

    boolean modifystatus(Long ono);

    boolean modifystatus2(Long ono);
    boolean modifystatus3(Long ono);
}
