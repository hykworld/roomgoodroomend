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
//////////////이메일추가//////////////////////////////////////////////추가.end////////////////////////////////
    public void sendCodeToEmail(String toEmail);

    public String createCode();
    public boolean checkCode(String email, String code);

    public boolean passwordChange(String email,String password);

}
