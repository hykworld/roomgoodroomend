package com.room.good.security.handler;

import com.room.good.entity.ClubMember;
import com.room.good.repository.ClubMemberRepository;
import com.room.good.security.dto.ClubAuthMemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Optional;

@Log4j2
public class ClubLoginSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();
    @Autowired
    private ClubMemberRepository repository;


    private PasswordEncoder passwordEncoder;

    public ClubLoginSuccessHandler(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }



    //따로 불르고 있는 곳은 없고 Authentication이 성공했을 때 자동으로 부르게 되는 매서드!
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {


        log.info("--------------------------------------");
        log.info("onAuthenticationSuccess");

        ClubAuthMemberDTO authMember = (ClubAuthMemberDTO)authentication.getPrincipal();

        boolean fromSocial = authMember.isFromSocial(); // 소셜로그인 여부.

        log.info("Need Modify Member?" + fromSocial);

        //matches() 사용자가 입력한 비밀번호와 테이블에 암호화되어 있는 비밀번호를 비교해서 같으면 true.
        //1111이 비밀번호라고 가정한 하드코딩...


        log.info("authMember" + authMember);
        Optional<ClubMember> member = repository.findByEmail(authMember.getEmail(),true);

        HttpSession session = request.getSession();
        session.setAttribute("id", member.get().getId());
        session.setAttribute("email", authMember.getEmail());

        boolean passwordResult = passwordEncoder.matches("1111", authMember.getPassword());//왼쪽은 입력값 , 오른쪽이 디비값


        if(member.get().getPhone()==null){
            log.info("here");
            redirectStratgy.sendRedirect(request, response, "/join");
        }
        else if(passwordResult && fromSocial){//초기 비밀번호를 1111입력 후 , 안바꿨을 때 ! 
            log.info("success!!");
            redirectStratgy.sendRedirect(request, response, "/join");//수정페이지로 가라
        }
        else {
            log.info("nosuccess!!");// 바꿔서 1111과 같지 않을 때 ! 아 이제야 알겟다
        redirectStratgy.sendRedirect(request, response, "/index");//수정페이지로 가라
        }
    }

}