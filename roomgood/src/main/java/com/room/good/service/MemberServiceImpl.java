package com.room.good.service;

import com.room.good.dto.MemberDTO;
import com.room.good.email.MailService;
import com.room.good.entity.ClubMember;
import com.room.good.entity.ClubMemberRole;
import com.room.good.exception.BusinessLogicException;
import com.room.good.exception.ExceptionCode;
import com.room.good.redis.RedisService;
import com.room.good.repository.ClubMemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Optional;
import java.util.Random;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    //
    private final ClubMemberRepository clubMemberRepository;

    // 이메일// 이메일// 이메일// 이메일// 이메일// 이메일// 이메일// 이메일// 이메일// 이메일
    private static final String AUTH_CODE_PREFIX = "AuthCode ";
    private final RedisService redisService;
    private final MailService mailService;
    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;

    // 이메일// 이메일// 이메일// 이메일// 이메일// 이메일// 이메일// 이메일// 이메일// 이메일

    @Override
    public boolean join(MemberDTO memberDTO) {

//        Optional<ClubMember> a = repository.findByPhone(memberDTO.getPhone());

        if(clubMemberRepository.findByPhone(memberDTO.getPhone()).isPresent()){
            return false;
        }
        Optional<ClubMember> clubMember = clubMemberRepository.findById(memberDTO.getId());
        if(clubMember.isPresent()){
        ClubMember c = clubMember.get();
        c.setName(memberDTO.getName());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        c.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        c.setPhone(memberDTO.getPhone());
        c.setBirth(memberDTO.getBirth());
        c.setNickname(memberDTO.getNickname());
        c.setStreetaddress(memberDTO.getStreetaddress());
        c.setDetailaddress(memberDTO.getDetailaddress());
        c.setGrade(memberDTO.getGrade());
        c.setMoney(memberDTO.getMoney());
        c.setAdress(memberDTO.getAdress());
        c.setMileage(memberDTO.getMileage());

            clubMemberRepository.save(c);
        }

        return true;

    }

    @Override
    public boolean check(String email) {

        Optional<ClubMember> member= clubMemberRepository.findByEmail2(email);
        if (member.isPresent()){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean memberJoin(MemberDTO memberDTO) {
        ClubMember clubMember = new ClubMember();
        clubMember.setEmail(memberDTO.getEmail());

        //해시알고리즘사용중~
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        clubMember.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        //해시알고리즘사용중~.end

        /////////////이렇게 해야 스트링으로 받은거 추가 가능!! enum!

        String role = "MANAGER";
        ClubMemberRole memberRole = ClubMemberRole.valueOf(role);
        clubMember.addMemberRole(memberRole);
        ////////////////////////////////////////////////////

        clubMember.setPhone(memberDTO.getPhone());
        clubMember.setName(memberDTO.getName());
        clubMember.setNickname(memberDTO.getNickname());
        clubMember.setBirth(memberDTO.getBirth());
        clubMember.setFromSocial(false);// 여기 자체 회원가입이라!
        clubMember.setCompany("회사없음!");
        clubMember.setMileage(0L);
        clubMember.setMoney(0L);
        clubMember.setGrade("일반회원");
        clubMember.setAdress(memberDTO.getAdress());
        clubMember.setDetailaddress(memberDTO.getDetailaddress());
        clubMember.setStreetaddress(memberDTO.getStreetaddress());
        clubMember.addMemberRole(ClubMemberRole.USER);

        clubMember.setStreetaddress(memberDTO.getStreetaddress());
        clubMember.setDetailaddress(memberDTO.getDetailaddress());

        log.info("clubMember_clubMember"+clubMember);
        clubMemberRepository.save(clubMember);
        return true;
    }

    @Override
    public boolean membermodify(MemberDTO memberDTO) {
        Optional<ClubMember> byEmail2 = clubMemberRepository.findByEmail2(memberDTO.getEmail());
        ClubMember clubMember = byEmail2.get();
        clubMember.setName(memberDTO.getName());
        clubMember.setAdress(memberDTO.getAdress());
        clubMember.setStreetaddress(memberDTO.getStreetaddress());
        clubMember.setDetailaddress(memberDTO.getDetailaddress());
        clubMember.setNickname(memberDTO.getNickname());
        clubMember.setPhone(memberDTO.getPhone());
        clubMember.setBirth(memberDTO.getBirth());
        log.info("byEmail2byEmail2"+clubMember);
        clubMemberRepository.save(clubMember);

        return false;
    }

    @Override
    public MemberDTO findbyid(String email) {

        Optional<ClubMember> byId = clubMemberRepository.findByEmail2(email);
        ClubMember clubMember = byId.get();
        MemberDTO memberDTO = new MemberDTO().builder()
                .email(clubMember.getEmail())
                .name(clubMember.getName())
                .phone(clubMember.getPhone())
                .adress(clubMember.getAdress())
                .streetaddress(clubMember.getStreetaddress())
                .detailaddress(clubMember.getDetailaddress())
                .money(clubMember.getMoney())
                .grade(clubMember.getGrade())
                .nickname(clubMember.getNickname())
                .mileage(clubMember.getMileage())
                .nickname(clubMember.getNickname())
                .birth(clubMember.getBirth())
                .build();
        log.info(byId.get()+"byId.get()byId.get()byId.get()byId.get()");
        log.info(memberDTO+"service_memberDTO");


        return memberDTO;
    }
    ///////////////////////////////////////////////////////////////////이메일 추가////////
    ///////////////////////////////////////////////////////////////////이메일 추가////////
    ///////////////////////////////////////////////////////////////////이메일 추가////////
    ///////////////////////////////////////////////////////////////////이메일 추가////////

    public void sendCodeToEmail(String toEmail) {
        log.info("여기찍히나?");
        String title = "Travel with me 이메일 인증 번호";
        String authCode = this.createCode();
        log.info("authCodeauthCode?"+authCode);
        Optional<ClubMember> byEmail = clubMemberRepository.findByEmail2(toEmail);
        ClubMember clubMember = byEmail.get();
        clubMember.setCode(authCode);
        clubMemberRepository.save(clubMember);
        mailService.sendEmail(toEmail, title, authCode);
        log.info("sendEmailsuccess");
        // 이메일 인증 요청 시 인증 번호 Redis에 저장 ( key = "AuthCode " + Email / value = AuthCode )

//        redisService.setValues(AUTH_CODE_PREFIX + toEmail,
//                authCode, Duration.ofMillis(this.authCodeExpirationMillis));
    }
    public boolean checkCode(String email, String code){

        Optional<ClubMember> byEmail = clubMemberRepository.findByEmail2(email);
        if(code.equals(byEmail.get().getCode())){//코드가 내가 저장하고 이멜로 보내준 거랑 일치하면 !
            return true;
        }else {

            return false;
        }


    }

    @Override
    @Transactional
    public boolean passwordChange(String email,String password) {
        log.info("serviceEmail"+email);
        log.info("servicepassword"+password);
        Optional<ClubMember> byEmail2 = clubMemberRepository.findByEmail2(email);
        log.info("byEmail2byEmail2"+byEmail2);
        ClubMember clubMember = byEmail2.get();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //해싱 알고리즘!!
        clubMember.setPassword(passwordEncoder.encode(password));
        log.info("clubMemberclubMemberclubMember"+clubMember);
        log.info(clubMember.getPassword());
        clubMemberRepository.save(clubMember);
        return true;
    }

    @Override
    public String createCode() {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("MemberService.createCode() exception occur");
            throw new BusinessLogicException(ExceptionCode.NO_SUCH_ALGORITHM);
        }

    }

    }
