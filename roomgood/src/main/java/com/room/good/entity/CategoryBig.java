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
public class CategoryBig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cbno;
    private String cname;  //
    private String deliveryFee; //배송비
    private String refund; //환불비

}
