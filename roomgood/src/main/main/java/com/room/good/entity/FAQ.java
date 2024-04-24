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
public class FAQ extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Faqno;// 프라이머리 키
    private String Faqtitle;// 제목
    private String Faqcontent;//내용
    private String status; ///이걸 기준으로 분류를 하면 될 거 같고

// 글만 있어도 될 듯
}

