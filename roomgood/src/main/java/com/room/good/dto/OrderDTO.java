package com.room.good.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long ono;

    @Builder.Default
    private List<OrderItemDTO> orderItemDTO = new ArrayList<>();//movieImage

    private Long price;
    private String regDate;// baseEntity
    private String modDate;// baseEntity

}
