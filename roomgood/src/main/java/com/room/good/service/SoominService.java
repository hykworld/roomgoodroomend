package com.room.good.service;

import com.room.good.dto.*;
import com.room.good.entity.Product;

import java.util.List;

public interface SoominService {


    List<ProductListDTO> getProductList(PageRequestDTO pageRequestDTO);
    Long getCountAll();
    List<TimeSaleDTO> getTimeSaleList();

    List<ReviewDTO> getReviewListAll(Long pno, PageRequestDTO pageRequestDTO);
    void reviewRegister(ReviewDTO reviewDTO);
    void reviewModify(ReviewDTO reviewDTO);
    void reviewDelete(Long rnum);

    Long getCountOfProduct(Long pno);


}
