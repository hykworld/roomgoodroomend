package com.room.good.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class TimeSale {
    //기본 테이블 ID줄것
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    // 타임세일 물품 이미지
    private String img;

    //글 내용
    private String content;

    //상품페이지 어디로 이동될껀지 url
    private String url;

    //오리지널 가격
    private Long originalPrice;

    //할인율
    private Long sale;

    //끝나는 시간 년 월 일 시간순으로 입력해야하고 이건 일단 하드코딩할 예정
    private String endTime;
}
