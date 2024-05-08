package com.room.good.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {
    private Long pno;

    private Long rnum;

    private int grade;

    private Long mid;

    private String nickName;

    private String text;

    private LocalDateTime regDate, modDate;
}
