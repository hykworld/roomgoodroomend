package com.room.good.entity;
import com.room.good.constant.ItemSellStatus;
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

    @Column(nullable = false, length = 255)// null값 안됨
    //length = 255 이 기본값 공부용으로 남김
    private String pname;//상품명

   @Column(nullable = false)// null값 안됨
    private Long stock; //재고
    private Long price;//가격

    //4.25 enum으로
    //private boolean isPresent;// 제품상태 itemSellSattus;

    @Lob//데이터를 어디에 저장할지 2가지 타입 중에 선택해서 외부저장
    // CLOB=텍스트 BLOB=미디어파일
    @Column(nullable = true)//(null가능)
    private String content;//

    private String subContent; // 간략한 설명 // 4.25컬럼 추가
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

// 이미지는 따로 하는건가?
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Tag> tagSet = new HashSet<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private CategoryBig categoryBig;

    //@ManyToOne(fetch = FetchType.LAZY)
    //private Admin admin;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Order1 order1;

    // 필요한 생성자 추가
    public Product(Long pno, String pname, Long stock) {
        this.pno = pno;
        this.pname = pname;
        this.stock = stock;
    }
}
