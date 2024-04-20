package com.room.good.security.service;


import com.room.good.entity.ClubMember;
import com.room.good.entity.ClubMemberRole;
import com.room.good.repository.ClubMemberRepository;
import com.room.good.security.dto.ClubAuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ClubOAuthUserDetailsService extends DefaultOAuth2UserService {
    //소셜 로그인용 !! 로그인한 유저의 정보를 가져오기위해서 필요한게 이거임
    private final ClubMemberRepository repository;
    private final PasswordEncoder passwordEncoder;
    String company = "notsosical";
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
        //인증을 요청할 때 loadUser()가 실행된다.

        log.info("=====================================================");
        log.info("userRequest: " + userRequest);

        String clientName = userRequest.getClientRegistration().getClientName(); // 서비스회사 ex 구글, 네이버
        //첫번째 -서비스회사 확인
        log.info("서비스회사: " + clientName);
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User =super.loadUser(userRequest);
        //두번째 -부모 클래스인 DefaultOAuth2UserService 의 loadUser()실행 -> OAuth2 프로바이더에서 사용자 정보를 가져오는 기본적인 동작을 수행한다.
        // 즉 , 소셜 로그인을 통해 인증되면 해당 사용자의 정보를 가져와서 OAuth2User 객체로 반환
        log.info("=====================================================");
        log.info("=====================================================");
        log.info(oAuth2User);
        log.info("=====================================================");
        log.info("=====================================================");

        oAuth2User.getAttributes().forEach((k,v) -> {
            log.info(k+":"+v);
        });

        String email = "";



        if(clientName.equals("Google")){
            email = oAuth2User.getAttribute("email");
            company ="google";
        }else if(clientName.equals("Kakao")){
            Map<String, Object> attributes = oAuth2User.getAttributes();
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
            company ="kakao";
            email = (String) kakaoAccount.get("email");
        }else if(clientName.equals("Naver")){
            Map<String, Object> attributes = oAuth2User.getAttributes();
            Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("response");
            company ="naver";
            email = (String) kakaoAccount.get("email");
        };


        log.info(company);
        log.info(email);
        log.info("EMAIL: " + email);//디버깅
        ClubMember member = saveSocialMember(email);
        log.info(member);
        ClubAuthMemberDTO clubAuthMember = new ClubAuthMemberDTO(
                member.getEmail(),
                member.getPassword(),
                true,
                member.getCompany(),
                member.getRoleSet().stream().map(
                        role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                        .collect(Collectors.toList()),
                oAuth2User.getAttributes()
                );

        clubAuthMember.setName(member.getName());
        return clubAuthMember;

    }

    private ClubMember saveSocialMember(String email){
        //기존에 동일한 이메일로 가입한 회원이 있는 경우에는 그대로 조회만
        Optional<ClubMember> result = repository.findByEmail(email, true);
        log.info("resultresult:"+result);
        if(result.isPresent()){
            return result.get();
        }



        //없다면 회원 추가 패스워드는 1111 이름은 그냥 이메일 주소로
        ClubMember clubMember = ClubMember.builder().email(email)
            .name(email)
            .company(company)
            .password( passwordEncoder.encode("1111"))
            .fromSocial(true)
            .build();

        clubMember.addMemberRole(ClubMemberRole.USER);
        clubMember.addMemberRole(ClubMemberRole.ADMIN);
        clubMember.addMemberRole(ClubMemberRole.MANAGER);

        repository.save(clubMember);

        return clubMember;
    }
}
