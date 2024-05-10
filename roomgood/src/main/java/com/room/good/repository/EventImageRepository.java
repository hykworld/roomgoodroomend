package com.room.good.repository;

import com.room.good.entity.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventImageRepository extends JpaRepository<EventImage,Long> {



    // select product.* ,productImage.*  from product left join productImage on product ;

    // select p ,pi from product p left join product_Image pi on p.pno = pi.product_pno;

    List<EventImage> findByEventEno(Long eno);
}
