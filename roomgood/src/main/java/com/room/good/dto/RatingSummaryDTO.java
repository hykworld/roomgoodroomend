package com.room.good.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor// 기본생성자
@AllArgsConstructor// 전부생성자
public class RatingSummaryDTO {
    private long totalRatings;
    private Double averageGrade;
}
