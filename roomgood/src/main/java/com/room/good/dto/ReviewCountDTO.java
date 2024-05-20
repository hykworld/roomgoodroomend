package com.room.good.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor// 기본생성자
@AllArgsConstructor// 전부생성자
public class ReviewCountDTO {
    private Long oneStar; //리뷰 1점 갯수
    private Long twoStar; // 리뷰 2점 갯수
    private Long threeStar; // 리뷰 3점 갯수
    private Long fourStar; // 리뷰 4점 갯수
    private Long fiveStar; // 리뷰 5점 갯수
}
