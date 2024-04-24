package com.room.good.service;



import com.room.good.dto.MemberDTO;

public interface MemberService {


    ///////////////추가////////////////////////////////
    boolean join(MemberDTO memberDTO);
    boolean check(String email);

    boolean memberJoin(MemberDTO memberDTO);
    boolean membermodify(MemberDTO memberDTO);

    MemberDTO findbyid(String email);
    ///////////////추가.end////////////////////////////////


}
