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
public class Review extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product; //LAZY

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubMember clubMember;

    private int grade;

    private String text;

    public void changeGrade(int grade){
        this.grade = grade;
    }
    public void changeText(String text){
        this.text = text;
    }
}

