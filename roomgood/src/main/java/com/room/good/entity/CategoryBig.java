package com.room.good.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class CategoryBig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cbno;
    private String cname;  //
    private String deliveryFee; //배송비
    private String refund; //환불비

}