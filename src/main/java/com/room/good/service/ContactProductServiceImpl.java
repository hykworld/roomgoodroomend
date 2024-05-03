package com.room.good.service;

import com.room.good.dto.ContactProductDTO;
import com.room.good.entity.ContactProduct;
import com.room.good.entity.Product;
import com.room.good.repository.ContactProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ContactProductServiceImpl implements ContactProductService {

    private final ContactProductRepository contactProductRepository;


    @Override
    public List<ContactProductDTO> getListOfProduct(Long pno) {
        Product product = Product.builder().pno(pno).build();
        List<ContactProduct> result = contactProductRepository.findByProduct(product);

        return result.stream().map(list -> entityToDto(list)).collect(Collectors.toList());
    }

    @Override
    public Long register(ContactProductDTO contactProductDTO) {
        ContactProduct contactProduct = dtoToEntity(contactProductDTO);
        contactProductRepository.save(contactProduct);

        return  contactProduct.getCpnum();
    }

    @Override
    public void modify(ContactProductDTO contactProductDTO) {
        Optional<ContactProduct> result = contactProductRepository.findById(contactProductDTO.getCpnum());

        if(result.isPresent()){
            ContactProduct contactProduct = result.get();

            contactProduct.setCpcontent(contactProductDTO.getCpcontent());
            contactProduct.setCptitle(contactProductDTO.getCptitle());

            contactProductRepository.save(contactProduct);
        }


    }

    @Override
    public void remove(Long cpnum) {

            contactProductRepository.deleteById(cpnum);


    }
}
