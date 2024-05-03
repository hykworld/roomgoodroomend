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
@ToString(exclude = {"categoryMiddle"})
public class CategorySmall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long csno;
    private String csname;  //

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private CategoryBig categoryMiddle;

}
