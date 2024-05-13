package com.room.good.dto;

import com.room.good.entity.CartItem;
import com.room.good.entity.WishList;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WishListDTO {

    @Builder.Default
    private List<WishList> wishlist = new ArrayList<>(); // 상품목록
}
