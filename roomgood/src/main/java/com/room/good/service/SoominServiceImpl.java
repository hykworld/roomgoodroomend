package com.room.good.service;

import com.room.good.dto.TimeSaleDTO;
import com.room.good.repository.TimeSaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class SoominServiceImpl implements SoominService{

    private final TimeSaleRepository timeSaleRepository;


    @Override
    public List<TimeSaleDTO> getTimeSaleList() {

        List<TimeSaleDTO> timeSaleDTOList = timeSaleRepository.findAll().stream().map(timeSale ->  TimeSaleDTO.builder()
                .endTime(timeSale.getEndTime())
                .tno(timeSale.getTno())
                .img(timeSale.getImg())
                .url(timeSale.getUrl())
                .content(timeSale.getContent())
                .endTime(timeSale.getEndTime())
                .build()).collect(Collectors.toList());


        return timeSaleDTOList;
    }
}
