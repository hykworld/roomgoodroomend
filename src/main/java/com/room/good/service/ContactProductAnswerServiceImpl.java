package com.room.good.service;

import com.room.good.dto.ContactProductAnswerDTO;
import com.room.good.dto.ContactProductDTO;
import com.room.good.entity.ContactProduct;
import com.room.good.entity.ContactProductAnswer;
import com.room.good.repository.ContactProductAnswerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ContactProductAnswerServiceImpl implements ContactProductAnswerService{
    private final ContactProductAnswerRepository repository;
    @Override
    public List<ContactProductAnswerDTO> getCPA(Long cpnum) {
        ContactProduct contactProduct = ContactProduct.builder().cpnum(cpnum).build();

        List<ContactProductAnswer> contactProductAnswer = repository.findByContactProduct(contactProduct);

        return contactProductAnswer.stream().map(arr -> ContactProductAnswerDTO.builder()
                .cpanum(arr.getCpanum())
                .cpacontent(arr.getCpacontent())
                .cpnum(arr.getContactProduct().getCpnum())
                .build()).collect(Collectors.toList());
    }

    @Override
    public void cpaRegister(ContactProductAnswerDTO dto) {
        ContactProductAnswer contactProductAnswer = ContactProductAnswer.builder()
                .contactProduct(ContactProduct.builder().cpnum(dto.getCpnum()).build())
                .cpanum(dto.getCpanum())
                .cpacontent(dto.getCpacontent())
                .build();

        repository.save(contactProductAnswer);

    }

    @Override
    public void cpaModify(ContactProductAnswerDTO dto) {
        Optional<ContactProductAnswer> contactProductAnswer = repository.findById(dto.getCpanum());

        if(contactProductAnswer.isPresent()){
            ContactProductAnswer result = contactProductAnswer.get();
            result.setCpacontent(dto.getCpacontent());

            repository.save(result);
        }


    }

    @Override
    public void cpaDelete(Long cpanum) {

        repository.deleteById(cpanum);
    }
}
