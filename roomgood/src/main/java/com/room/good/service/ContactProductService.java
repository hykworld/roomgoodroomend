package com.room.good.service;

import com.room.good.dto.ContactProductDTO;
import com.room.good.entity.ClubMember;
import com.room.good.entity.ContactProduct;
import com.room.good.entity.Product;

import java.util.List;

public interface ContactProductService {
    List<ContactProductDTO> getListOfProduct(Long pno);

    Long register(ContactProductDTO contactProductDTO);

    void modify(ContactProductDTO contactProductDTO);

    void remove(Long cpnum);

    default ContactProduct dtoToEntity(ContactProductDTO contactProductDTO){
        ContactProduct contactProduct = ContactProduct.builder()
                .cpnum(contactProductDTO.getCpnum())
                .clubMember(ClubMember.builder().id(contactProductDTO.getMid()).build())
                .product(Product.builder().pno(contactProductDTO.getPno()).build())
                .cpcontent(contactProductDTO.getCpcontent())
                .cptitle(contactProductDTO.getCptitle())
                .build();

        return contactProduct;
    }

    default ContactProductDTO entityToDto(ContactProduct contactProduct){
        ContactProductDTO contactProductDTO = ContactProductDTO.builder()
                .cpnum(contactProduct.getCpnum())
                .mid(contactProduct.getClubMember().getId())
                .pno(contactProduct.getProduct().getPno())
                .nickName(contactProduct.getClubMember().getNickname())
                .cpcontent(contactProduct.getCpcontent())
                .cptitle(contactProduct.getCptitle())
                .modDate(contactProduct.getModDate())
                .regDate(contactProduct.getRegDate())
                .build();

        return contactProductDTO;
    }

}
