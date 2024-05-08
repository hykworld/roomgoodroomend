package com.room.good.controller;

import com.room.good.dto.MainPagePageRequestDTO;
import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.ProductListDTO;
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
    private final SoominService soominService;

    @GetMapping("/{page}/all")
    public ResponseEntity<List<ProductListDTO>> getProductList(PageRequestDTO pageRequestDTO){
        pageRequestDTO.setSize(12);
        List<ProductListDTO> productListDTOList = soominService.getProductList(pageRequestDTO);

        return new ResponseEntity<>(productListDTOList, HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount(){
        Long count= soominService.getCountAll();

        return new ResponseEntity<>(count,HttpStatus.OK);
    }

}
