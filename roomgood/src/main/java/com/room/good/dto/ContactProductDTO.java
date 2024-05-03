package com.room.good.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactProductDTO {
    private Long cpnum;

    //product no
    private Long pno;

    //Member id
    private Long mid;

    //Member nickname
    private String nickName;

    private String cptitle;

    private String cpcontent;

    private LocalDateTime regDate, modDate;
}
