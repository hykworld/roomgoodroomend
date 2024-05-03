package com.room.good.service;

import com.room.good.dto.ContactProductAnswerDTO;

import java.util.List;

public interface ContactProductAnswerService {

    List<ContactProductAnswerDTO> getCPA(Long cpnum);

    void cpaRegister(ContactProductAnswerDTO dto);
    void cpaModify(ContactProductAnswerDTO dto);

    void cpaDelete(Long cpanum);
}
