package com.room.good.service;

import com.room.good.dto.OrderDTO;
import com.room.good.entity.Order1;
import com.room.good.entity.OrderItem;

import java.util.List;

public interface OrderrrService {

    boolean cartlistpay(String email,String receiver);

    List<OrderDTO> orderlist (Long id);
}
