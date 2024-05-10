package com.room.good.service;



import com.room.good.entity.FAQ;
import com.room.good.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final FaqRepository faqRepository;

    public void save(FAQ faq) {
        faqRepository.save(faq);
    }

    @Override
    public List<FAQ> getAllFAQs() {
        return faqRepository.findAll();
    }

    @Override
    public Optional<FAQ> getFAQById(Long faqno) {
        return faqRepository.findById(faqno);
    }

    @Override
    public void updateFAQ(FAQ faq) {
        faqRepository.save(faq);
    }

    @Override
    public void deleteFAQ(Long faqno) {
        faqRepository.deleteById(faqno);
    }
}