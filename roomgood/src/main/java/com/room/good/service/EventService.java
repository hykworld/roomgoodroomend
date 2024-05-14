package com.room.good.service;

import com.room.good.dto.EventDTO;
import com.room.good.dto.EventImageDTO;
import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.PageResultDTO;
import com.room.good.entity.Event;
import com.room.good.entity.EventImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface EventService {

    PageResultDTO<EventDTO, Object[]> getList(PageRequestDTO requestDTO); // 여기서 제네릭의 장점 보임

    Long register(EventDTO eventDTO);

    EventDTO read(Long eno);

    boolean delete(Long eno);



    default EventDTO entitiesToDTO(Event event, List<EventImage> EventImages){
        EventDTO eventDTO = EventDTO.builder()
                .eno(event.getEno())
                .title(event.getTitle())
                .regDate(event.getRegDate())
                .modDate(event.getModDate())
                .expired(event.getExpired())
                .build();

        List<EventImageDTO> eventImageDTOList = EventImages.stream().map(EventImage -> {
            return EventImageDTO.builder().imgName(EventImage.getEiimgName())
                    .path(EventImage.getEipath())
                    .uuid(EventImage.getEiuuid())
                    .build();
        }).collect(Collectors.toList());

        eventDTO.setImageDTOList(eventImageDTOList);




        return eventDTO;

    }

    default Map<String, Object> dtoToEntity(EventDTO eventDTO){

        Map<String, Object> entityMap = new HashMap<>();

        Event event = Event.builder()
                .eno(eventDTO.getEno())
                .title(eventDTO.getTitle())
                .expired(eventDTO.getExpired())
                .build();

        entityMap.put("event", event);

        List<EventImageDTO> imageDTOList = eventDTO.getImageDTOList();

        if(imageDTOList != null && imageDTOList.size() > 0 ) { //MovieImageDTO 처리

            List<EventImage> eventImageList = imageDTOList.stream().map(eventImageDTO ->{

                EventImage eventImage = EventImage.builder()
                        .eipath(eventImageDTO.getPath())
                        .eiimgName(eventImageDTO.getImgName())
                        .eiuuid(eventImageDTO.getUuid())
                        .event(event)
                        .build();
                return eventImage;
            }).collect(Collectors.toList());

            entityMap.put("imgList", eventImageList);
        }

        return entityMap;
    }
}
