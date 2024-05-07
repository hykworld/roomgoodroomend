package com.room.good.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductListDTO {
    private String pname;
    private Long price;
}
