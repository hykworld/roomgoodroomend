package com.room.good.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString(exclude = {"contactProduct"})
public class ContactProductAnswer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cpanum;

    @ManyToOne
    private ContactProduct contactProduct;

    private String cpacontent;

}
