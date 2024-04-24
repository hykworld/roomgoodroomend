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
public class Event extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eno;

    private String title;

    private String content;
    private String expired;

}
