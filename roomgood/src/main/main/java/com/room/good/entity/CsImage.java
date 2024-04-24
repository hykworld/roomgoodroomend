package com.room.good.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(exclude = {"clubMember"})
public class CsImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long csino;
    private String csiuuid;
    private String csimgName;
    private String csipath;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubMember clubMember;
}

