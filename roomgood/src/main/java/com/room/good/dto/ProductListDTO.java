package com.room.good.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductListDTO {
    private Long pno;
    private String pname;
    private Long price;
}
