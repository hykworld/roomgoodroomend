package com.room.good.controller;


import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.ReviewDTO;
import com.room.good.service.SoominService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final SoominService soominService;

    @GetMapping("/{pno}/{page}/all")
    public ResponseEntity<List<ReviewDTO>> getReviewList(@PathVariable("pno") Long pno, PageRequestDTO pageRequestDTO){
        pageRequestDTO.setSize(5);
        List<ReviewDTO> reviewList = soominService.getReviewListAll(pno,pageRequestDTO);

        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }

    @GetMapping("/{pno}/count")
    public  ResponseEntity<Long> getRVCount(@PathVariable("pno") Long pno){
        Long count = soominService.getCountOfProduct(pno);
        return new ResponseEntity<>(count,HttpStatus.OK);
    }


    @PostMapping("/{pno}")
    public ResponseEntity<Void> reviewRegister(@RequestBody ReviewDTO reviewDTO){
        soominService.reviewRegister(reviewDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{pno}/{rnum}")
    public ResponseEntity<Void> reviewModify(@RequestBody ReviewDTO reviewDTO){
        soominService.reviewModify(reviewDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{pno}/{rnum}")
    public ResponseEntity<Void> reviewDelete(@PathVariable("rnum") Long rnum){
        soominService.reviewDelete(rnum);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
