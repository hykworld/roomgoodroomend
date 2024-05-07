package com.room.good.email;
import com.room.good.entity.ClubMember;
import com.room.good.exception.BusinessLogicException;
import com.room.good.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 작성자: 김찬빈
 * 버전 정보: 1.0.0
 * 작성일자: 2023/04/20
 **/
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender emailSender;


    public void sendEmail(String toEmail,String title, String text) {
        log.info("toEmail"+toEmail);
        log.info("title"+title);
        log.info("text"+text);
        String sendText = "인증번호는" + text + "입니다 . ";

        SimpleMailMessage emailForm = this.createEmailForm(toEmail, title, sendText);
        emailForm.setFrom("ahkoo121212@gmail.com");

        try {
            log.info("emailForm"+emailForm);
            emailSender.send(emailForm);
        } catch (RuntimeException e) {
            log.debug("MailService.sendEmail exception occur toEmail: {}, " +
                    "title: {}, text: {}", toEmail, title, text);
            throw new BusinessLogicException(ExceptionCode.UNABLE_TO_SEND_EMAIL);
        }
    }

    // 발신할 이메일 데이터 세팅
    private SimpleMailMessage createEmailForm(String toEmail,
                                              String title,
                                              String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(title);
        message.setText(text);

        return message;
    }
}