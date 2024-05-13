package com.room.good.controller;

import com.room.good.dto.*;
import com.room.good.service.ProductService;
import com.room.good.service.SoominService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/productList")
@RequiredArgsConstructor
public class SubControllerSumin {
    private final ProductService productService;
    private final SoominService soominService;

    @GetMapping("/{page}/all")
    public ResponseEntity<PageResultDTO<ProductDTO, Object[]>> getProductList(PageRequestDTO pageRequestDTO){
        pageRequestDTO.setSize(12);
        PageResultDTO<ProductDTO, Object[]> pageResultDTO = productService.getList(pageRequestDTO);
        log.info("===================="+pageResultDTO);


        return new ResponseEntity<>(pageResultDTO, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount(){
        Long count= soominService.getCountAll();

        return new ResponseEntity<>(count,HttpStatus.OK);
    }

}
