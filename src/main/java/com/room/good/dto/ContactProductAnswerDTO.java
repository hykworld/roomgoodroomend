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
public class ContactProductAnswerDTO {
    private Long cpanum;

    private Long cpnum;

    private String cpacontent;

    private LocalDateTime regDate, modDate;
}
