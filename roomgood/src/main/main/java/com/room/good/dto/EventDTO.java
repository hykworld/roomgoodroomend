package com.room.good.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private Long eno;//

    private String title;
    private String content;

    @Builder.Default
    private List<EventImageDTO> imageDTOList = new ArrayList<>();//movieImage

    private String expired;

    private LocalDateTime regDate;//movie의 baseEntity

    private LocalDateTime modDate;//movie의 baseEntity

}
