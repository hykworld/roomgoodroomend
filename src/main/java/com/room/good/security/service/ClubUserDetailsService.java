package com.room.good.security.service;

import com.room.good.entity.ClubMember;
import com.room.good.repository.ClubMemberRepository;
import com.room.good.security.dto.ClubAuthMemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClubUserDetailsService implements UserDetailsService {
    //일반 로그인용 !! 로그인한 유저의 정보를 가져오기위해서 필요한게 이거임
    private final ClubMemberRepository clubMemberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("ClubUserDetailsService loadUserByUsername " + username);

        Optional<ClubMember> result = clubMemberRepository.findByEmail(username, false);
        Optional<ClubMember> result2 = clubMemberRepository.findByEmail2(username);

        if(result2.isEmpty()){
            log.info("um herherre");
            throw new UsernameNotFoundException("Check User Email or from Social ");
        }

        ClubMember clubMember = result2.get();

        log.info("-----------------------------");
        log.info(clubMember);

        ClubAuthMemberDTO clubAuthMember = new ClubAuthMemberDTO(
                clubMember.getEmail(),
                clubMember.getPassword(),
                clubMember.isFromSocial(),
                clubMember.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                        .collect(Collectors.toSet())
        );
        clubAuthMember.setPassword(clubMember.getPassword());
        clubAuthMember.setName(clubMember.getName());
        clubAuthMember.setFromSocial(clubMember.isFromSocial());

        return clubAuthMember;
    }
}
