package com.room.good.controller;

import com.room.good.dto.ContactProductDTO;
import com.room.good.dto.PageRequestDTO;
import com.room.good.service.ContactProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contactProduct")
@Log4j2
@RequiredArgsConstructor
public class ContactProductController {

    private final ContactProductService contactProductService;

    //목록불러오기
    @GetMapping("/{pno}/{page}/all")
    public ResponseEntity<List<ContactProductDTO>> getCPList(@PathVariable("pno") Long pno, PageRequestDTO pageRequestDTO){
        pageRequestDTO.setSize(5);
        List<ContactProductDTO> dtoList = contactProductService.getListOfProduct(pno,pageRequestDTO);

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    //페이징 불러오기
    @GetMapping("/{pno}/count")
    public ResponseEntity<Long> getCPCount(@PathVariable("pno") Long pno){
        Long count = contactProductService.getCountOfProduct(pno);

        return new ResponseEntity<>(count,HttpStatus.OK);
    }

    //추가할떄 리턴값 안받기인데 될지 모르겠음
    @PostMapping("/{pno}")
    public ResponseEntity<Long> registerCP(@RequestBody ContactProductDTO contactProductDTO){
        Long num = contactProductService.register(contactProductDTO);

        return new ResponseEntity<>(num, HttpStatus.OK);
    }

    @PutMapping("/{pno}/{cpnum}")
    public ResponseEntity<Void> modifyCP(@RequestBody ContactProductDTO contactProductDTO){
        log.info("--------------------------------------"+contactProductDTO);
        contactProductService.modify(contactProductDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{pno}/{cpnum}")
    public ResponseEntity<Void> deleteCP(@PathVariable Long cpnum){
        contactProductService.remove(cpnum);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
