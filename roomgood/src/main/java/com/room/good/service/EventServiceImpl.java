package com.room.good.service;


import com.room.good.dto.EventDTO;
import com.room.good.dto.EventImageDTO;
import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.PageResultDTO;
import com.room.good.entity.Event;
import com.room.good.entity.EventImage;
import com.room.good.repository.EventImageRepository;
import com.room.good.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

    private final EventRepository eventRepository;
    private final EventImageRepository eventImageRepository;
    @Override
    public PageResultDTO<EventDTO, Object[]> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("eno").descending());//mno 기준으로 솔팅!
        // 이 생성자는 Sort 타입의 인자를 받는 생성자 . -> 이렇게 만든 sort는 return PageRequest.of(page -1, size, sort);에 들어가서 리턴됨
        // 즉
        //pageable = PageRequest.of(page -1, size, Sort.by("mno").descending()); 이렇게 완성

        Page<Object[]> result = eventRepository.getListPage(pageable); //리스트를 페이징처리해서 가져옴


        log.info("==============================================");
        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });

        Function<Object[], EventDTO> fn = (arr -> entitiesToDTO( // fn은 지금 그릇 만드는중 -> 지금 arr에 뭐 들어가고 하는게 아니고 만약 <Object[], MovieDTO>여기안에 내용들 그득히
                //차있으면 그거에 따라서 밑에 방식으로 처리하겠다는 뜻.
                (Event)arr[0] ,
                (List<EventImage>)(Arrays.asList((EventImage)arr[1])) /// 이렇게 바꿀 수 있어요
        )
        );

        //  PageResultDTO<DTO,EN> pageResult = new PageResultDTO<>(result, fn);
        // return pageResult;  이거랑 같은 뜻임 . -> 즉 pageResult에 저 생성자 정보들
        return new PageResultDTO<>(result, fn); // 위에서 만들었던 펑션 -> <Object[], MovieDTO> 이 그릇을 가지고있는 fn과 result를 가지고 생성자를 호출중
    }
    @Transactional
    @Override
    public Long register(EventDTO eventDTO) {
        Map<String, Object> entityMap = dtoToEntity(eventDTO);
        Event event = (Event) entityMap.get("event");
        List<EventImage> eventImageList = (List<EventImage>) entityMap.get("imgList");
        event.setContent(eventDTO.getContent());
        eventRepository.save(event);

        eventImageList.forEach(eventImage -> {
            eventImageRepository.save(eventImage);
        });

        return event.getEno();
    }

    @Transactional
    @Override
    public EventDTO read(Long eno) {
        Optional<Event> byId = eventRepository.findById(eno);
        log.info(byId.get()+"byIdbyIdbyIdbyId");
        List<EventImage> byEventEno = eventImageRepository.findByEventEno(eno);
        EventImage thumnailImage = byEventEno.get(0);
        EventImage mainImage = byEventEno.get(1);

        List<EventImageDTO> a = new ArrayList<>();

        EventImageDTO eventImageDTO = new EventImageDTO(); // 썸넬
        eventImageDTO.setPath(thumnailImage.getEipath());
        eventImageDTO.setUuid(thumnailImage.getEiuuid());
        eventImageDTO.setImgName(thumnailImage.getEiimgName());
        a.add(eventImageDTO);
        EventImageDTO eventImageDTO2 = new EventImageDTO(); // 썸넬
        eventImageDTO2.setPath(mainImage.getEipath());
        eventImageDTO2.setUuid(mainImage.getEiuuid());
        eventImageDTO2.setImgName(mainImage.getEiimgName());
        a.add(eventImageDTO2);

        EventDTO eventDTO = EventDTO.builder().title(byId.get().getTitle())
                        .content(byId.get().getContent()).imageDTOList(a)
                                .expired(byId.get().getExpired()).
                build();




        log.info(eventDTO+"eventDTOeventDTOeventDTO");
        return eventDTO;
    }

    @Override
    @Transactional
    public boolean delete(Long eno) {
        eventImageRepository.deleteAllByEventEno(eno);
        eventRepository.deleteById(eno);


        return true;
    }
}
