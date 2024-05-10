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
@ToString(exclude = "product")
public class ProductImage2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pinum; // 엠리뷰 같음 지적하면 선생님한테 이름
    private String piuuid; // 이미지 파일명
    private String piimgName; //원본 이미지 파일명
    private String pipath; // 이미지 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

//    public void updateProductImage(String piimgName,String piuuid,String pipath){
//        this.piimgName= piimgName; // 원본이름
//        this.piuuid=piuuid; // 이미지 파일명
//        this.pipath=pipath; // 경로
//    }
}
