package com.room.good.controller;

import com.room.good.dto.ContactProductDTO;
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
    @GetMapping("/{pno}/all")
    public ResponseEntity<List<ContactProductDTO>> getCPList(@PathVariable("pno") Long pno){
        List<ContactProductDTO> dtoList = contactProductService.getListOfProduct(pno);

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
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
