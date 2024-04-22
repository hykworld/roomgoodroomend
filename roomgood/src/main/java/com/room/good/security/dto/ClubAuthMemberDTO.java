package com.room.good.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Log4j2
@Getter
@Setter
@ToString
public class ClubAuthMemberDTO extends User implements OAuth2User {

    private String email;

    private String password;

    private String name;

    private boolean fromSocial;

    private String company;

    private String phone;

    private String adress;// 주소
    private String streetaddress;		// 지번 주소
    private String detailaddress;		// 상세

    private Map<String, Object> attr;

//    private Map<String, Object> attributes;

//    public ClubAuthMemberDTO(String username, String password, boolean fromSocial,
//                             Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
//        this(username,password, fromSocial, authorities);
//        this.attr = attr;
//    }

    public ClubAuthMemberDTO(String username, String password, boolean fromSocial, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.email = username;
        this.password = password;
        this.fromSocial = fromSocial;
    }


    public ClubAuthMemberDTO(String username, String password, boolean fromSocial, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
        this(username,password, fromSocial, authorities);
        this.attr = attr;
    }

    public ClubAuthMemberDTO(String email, String password, boolean fromSocial, String company, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
        this(email,password, fromSocial, authorities);
        this.attr=attr;
        this.company=company;
    }

    public ClubAuthMemberDTO(String email, String password, String name , String phone, boolean fromSocial, String company, Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
        this(email,password, fromSocial, authorities);
        this.phone= phone;
        this.attr=attr;
        this.name=name;
        this.company=company;
    }


    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }
}