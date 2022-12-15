package org.zerock.sony.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.sony.member.entity.Member;
import org.zerock.sony.member.repository.MemberRepository;
import org.zerock.sony.security.dto.AuthMemberDTO;

import java.util.Optional;
import java.util.stream.Collectors;


@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService {
	private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("UserDetailsService loadUserByUserId " + username);
        Optional<Member> result = memberRepository.findByUserid(username, false);
        if(result.isEmpty()){
            throw new UsernameNotFoundException("Check User Id or from Social ");
        }

        Member member = result.get();
        log.info("-----------------------------");
        log.info(member);
        
        AuthMemberDTO AuthMember = new AuthMemberDTO(
        		member.getUserid(),
                member.getEmail(),
                member.getPwd(),
                member.isFromSocial(),
                member.getGrade(),
                member.getRoleSet().stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                        .collect(Collectors.toSet())
        );
        AuthMember.setName(member.getName());
        AuthMember.setFromSocial(member.isFromSocial());
        log.info(AuthMember);
//        return null;
        return AuthMember;
    }
}

