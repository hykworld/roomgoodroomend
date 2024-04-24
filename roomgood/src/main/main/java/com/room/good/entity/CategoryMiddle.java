package com.room.good.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(exclude = {"categoryBig"})
public class CategoryMiddle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cmno;
    private String cmname;  //

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CategoryBig categoryBig;

}
