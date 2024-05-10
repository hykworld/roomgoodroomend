package com.room.good.dto;


import com.room.good.entity.FAQ;
import lombok.*;

@Data
public class FaqDTO {
    private Long faqno;// 프라이머리 키
    private String faqtitle;// 제목
    private String faqcontent;//내용
    private String status;

    public FAQ toEntity() {
        FAQ faq = new FAQ();
        faq.setFaqno(this.faqno);
        faq.setFaqtitle(this.faqtitle);
        faq.setFaqcontent(this.faqcontent);
        faq.setStatus(this.status);
        return faq;
    }
}
