package com.room.good.service;

import com.room.good.dto.OrderDTO;

public interface OrderService {
    public Long order(OrderDTO orderDTO, String email);
}
