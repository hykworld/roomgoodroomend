package com.room.good.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class MainPagePageRequestDTO {

    private int page;
    private int size;
    private String type;
    private String keyword;


    public MainPagePageRequestDTO(){
        this.page = 1;
        this.size = 12;
    }

    public Pageable getPageable(Sort sort){

        return PageRequest.of(page -1, size, sort);

    }
}
