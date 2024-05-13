package com.room.good.entity;

import com.room.good.constant.ItemSellStatus;
import com.room.good.exception.OutOfStockException;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.NumberFormat;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    private Long originalPrice;//할인된가격

    private Long discount;//할인율

    @Lob//데이터를 어디에 저장할지 2가지 타입 중에 선택해서 외부저장
    // CLOB=텍스트 BLOB=미디어파일
    @Column(nullable = true)//(null가능)
    private String content;//

    private String subContent; // 간략한 설명 // 4.25컬럼 추가
    private ItemSellStatus itemSellStatus; // 상품 판매 상태

// 이미지는 따로 하는건가?
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Tag> tagSet = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cno") // CategoryBig 엔티티의 cno와 매핑
    private CategoryBig categoryBig; // Product와 CategoryBig의 관계 매핑

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


    // 제품 삭제시 연관 댓글 삭제 구현을 위한 One To Many

//    @OneToMany(mappedBy = "product", orphanRemoval = true)
//    private List<ContactProduct> contactProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProductImage> images = new ArrayList<>();

    @ManyToOne (fetch = FetchType.LAZY)
    private ClubMember clubMember;

    public void removeStock(Long stock){
        Long restStock = this.stock - stock; // 상품의 재고수량에서 판매수량을 빼서 남은재고수량 구하기
        if(restStock < 0){
            throw new OutOfStockException("상품의 재고가 부족합니다.(현재 재고 수량:" + this.stock + ")");
            // 상품의 재고가 주문 수량보다 작을 경우 재고 부족 예외처리
        }this.stock = restStock; // 주문 후 남은 재고 수량을 상품의 현재 재고 값으로 할당
    }

}
