package com.room.good.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;//고유번호
    private String pname;//이름
    private Long stock; //재고
    private Long price;//가격
    private boolean isPresent;// ture
    private String content;// (null가능)

// 이미지는 따로 하는건가?
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Tag> tagSet = new HashSet<>();



    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CategoryBig categoryBig;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Order1 order1;
}
