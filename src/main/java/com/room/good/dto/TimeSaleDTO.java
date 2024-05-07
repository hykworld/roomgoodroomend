package com.room.good.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimeSaleDTO {

    private Long tno;

    // 타임세일 물품 이미지
    private String img;

    //글 내용
    private String content;

    //상품페이지 어디로 이동될껀지 url
    private String url;

    //끝나는 시간 년 월 일 시간순으로 입력해야하고 이건 일단 하드코딩할 예정
    private String endTime;

}
