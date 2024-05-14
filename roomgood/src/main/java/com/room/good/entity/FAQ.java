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
    private Long faqno;// 프라이머리 키
    private String faqtitle;// 제목
    @Column(columnDefinition = "TEXT") // 데이터베이스에서 TEXT 타입으로 지정
    private String faqcontent;
    private String status; ///이걸 기준으로 분류를 하면 될 거 같고
    @Column(name = "regdate", insertable = false, updatable = false)
    private LocalDateTime regdate; // 작성일자

//// 글만 있어도 될 듯
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "faqno") // CategoryBig 엔티티의 cno와 매핑
//    private FaqCategory faqCategory; // Product와 CategoryBig의 관계 매핑
}

