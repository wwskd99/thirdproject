package org.zerock.sony;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.sony.member.entity.Member;
import org.zerock.sony.member.entity.MemberRole;
import org.zerock.sony.member.repository.MemberRepository;
import java.util.stream.IntStream;

import java.util.HashSet;

@SpringBootTest
public class test {
    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {
        //1 - 80까지는 USER만 지정
        //81- 90까지는 USER,MANAGER
        //91- 100까지는 USER,MANAGER,ADMIN


            Member member = Member.builder()
                    .userid("test")
                    .address("경기도")
                    .email("test@test.com")
                    .phone("010-0000-0000")
                    .name("test")
                    .fromSocial(false)
                    .roleSet(new HashSet<MemberRole>())
                    .pwd(passwordEncoder.encode("1234"))
                    .build();
    
            //default role
            member.addMemberRole(MemberRole.USER);
//            member.addMemberRole(MemberRole.ADMIN);

            repository.save(member);
    }
}
