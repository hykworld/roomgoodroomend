package com.room.good.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "event") //연관 관계시 항상 주의
public class EventImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eino;

    private String eiuuid;

    private String eiimgName;

    private String eipath;

    @ManyToOne(fetch = FetchType.LAZY) //무조건 lazy로
    private Event event;
}
