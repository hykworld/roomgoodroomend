package com.room.good.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class ClubMember extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Long 혹은 UUID와 같은 고유한 값으로 식별자를 생성합니다.

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String name;
    private String phone;

    private boolean fromSocial; // 의미없는데 ㄱㅊ
    ///////////////////


    private String adress;// 주소

    private String streetaddress;		// 지번 주소
    private String detailaddress;		// 상세

    @Column( columnDefinition = "BIGINT(20) DEFAULT 0")
    private Long money; // 충전금액
    @Column( columnDefinition = "BIGINT DEFAULT 0")
    private Long mileage; // 마일리지~
    @Column( columnDefinition = "VARCHAR(255) DEFAULT '일반회원'")
    private String grade;// 등급 ex vvip ,vip
    //nullable = false => NOT NULL
    @Column( columnDefinition = "VARCHAR(255) DEFAULT '회사없음'")
    private String company;


    //oneToMany여야되는데  @ElementCollection 이걸로 똑같이 함 이럴 땐 리스트가 와야된다. set 을 쓴 이유는 중복이 안되게함!
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<ClubMemberRole> roleSet = new HashSet<>();
    // n+1문제때문에 Set을 씀! 레퍼런스만 건다 8가지 기본타입은 쓸 수가 없다.


    public void addMemberRole(ClubMemberRole clubMemberRole){

        roleSet.add(clubMemberRole);
    }



}
