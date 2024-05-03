package com.room.good.service;

import com.room.good.dto.ProductListDTO;
import com.room.good.dto.TimeSaleDTO;
import com.room.good.entity.Product;

import java.util.List;

public interface SoominService {


    List<ProductListDTO> getProductList();
    List<TimeSaleDTO> getTimeSaleList();

}
