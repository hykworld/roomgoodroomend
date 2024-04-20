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
@ToString(exclude = {"clubMember"})
public class Cart extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;
    //
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE) // 프라이머리키 지워지면 얘네도 다 지워지는 -> 부모 지워지면 자식들도 지워지는 에너테이션
    private ClubMember clubMember;

    public Cart(ClubMember clubMember) {
        this.clubMember = clubMember;
    }
}