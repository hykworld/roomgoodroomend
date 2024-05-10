package com.room.good.dto;

import com.room.good.constant.ItemSellStatus;
import com.room.good.entity.CategoryBig;
import com.room.good.entity.CategoryMiddle;
import com.room.good.entity.Order1;
import com.room.good.entity.ProductImage;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor// 기본생성자
@AllArgsConstructor// 전부생성자
public class ProductDTO {

    private Long pno;

    private String pname;//상품명
    //private CategoryBig categoryBig; // 카테고리 대
    //private CategoryBig categoryMiddle; // 카테고리 중
    //private CategoryBig categorySmall; // 카테고리 소
    private Long price;//가격
    private Long stock; //재고
    private String content;// 제품설명
    private String subContent; // 간략한 설명 // 4.25컬럼 추가
    private ItemSellStatus itemSellStatus;//  SELL, SOLD_OUT

    private Order1 order1;

    private CategoryBig categoryBig; // CategoryBig 객체로 변경
//    private CategoryMiddle categoryMiddle; // CategoryMiddle 객체로 변경

    @Builder.Default
    private List<ProductImageDTO> imageDTOList= new ArrayList<>();

    @Builder.Default
    private List<ProductImageDTO> imageDTOList2= new ArrayList<>();

    //태그 어레이 리스트
}
