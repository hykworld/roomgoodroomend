package com.room.good.controller;

import com.room.good.dto.ContactProductAnswerDTO;
import com.room.good.dto.ContactProductDTO;
import com.room.good.service.ContactProductAnswerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cpanswer")
@Log4j2
@RequiredArgsConstructor
public class ContactProductAnswerController {
    private final ContactProductAnswerService service;
    @GetMapping("/{cpnum}")
    public ResponseEntity<List<ContactProductAnswerDTO>> getCPA(@PathVariable("cpnum") Long cpnum){
        List<ContactProductAnswerDTO> dto = service.getCPA(cpnum);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/{cpnum}")
    public ResponseEntity<Void> cpaRegister(@RequestBody ContactProductAnswerDTO contactProductAnswerDTO){
        service.cpaRegister(contactProductAnswerDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{cpnum}/{cpanum}")
    public ResponseEntity<Void> cpaModify(@RequestBody ContactProductAnswerDTO contactProductAnswerDTO){
        service.cpaModify(contactProductAnswerDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{cpnum}/{cpanum}")
    public ResponseEntity<Void> cpaDelete(@PathVariable("cpanum") Long cpanum){
        service.cpaDelete(cpanum);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
