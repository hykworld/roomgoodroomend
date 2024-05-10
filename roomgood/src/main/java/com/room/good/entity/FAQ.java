package com.room.good.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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
    @Column(name = "regdate", insertable = false, updatable = false)
    private LocalDateTime regdate; // 작성일자

// 글만 있어도 될 듯
}

