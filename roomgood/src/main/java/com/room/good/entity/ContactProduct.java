package com.room.good.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(exclude = {"product","clubMember"})
public class ContactProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cpnum;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubMember clubMember;

    private String cptitle;

    private String cpcontent;

    @OneToMany(mappedBy = "contactProduct", orphanRemoval = true)
    private List<ContactProductAnswer> contactProductAnswers;


}
