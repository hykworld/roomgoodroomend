package com.room.good.service;

import com.room.good.dto.EventDTO;
import com.room.good.dto.EventImageDTO;
import com.room.good.dto.PageRequestDTO;
import com.room.good.dto.PageResultDTO;
import com.room.good.entity.Event;
import com.room.good.entity.EventImage;
import com.room.good.entity.FAQ;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public interface FaqService {

   void save(FAQ faq);

   List<FAQ> getAllFAQs();

   Optional<FAQ> getFAQById(Long faqno);

   void updateFAQ(FAQ faq);

   void deleteFAQ(Long faqno);
}