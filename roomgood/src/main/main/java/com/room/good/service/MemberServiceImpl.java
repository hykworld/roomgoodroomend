package com.room.good.service;

import com.room.good.dto.MemberDTO;
import com.room.good.entity.ClubMember;
import com.room.good.entity.ClubMemberRole;
import com.room.good.repository.ClubMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    //
    private final ClubMemberRepository clubMemberRepository;

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

}
