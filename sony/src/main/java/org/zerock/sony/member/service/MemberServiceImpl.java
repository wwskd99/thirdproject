package org.zerock.sony.member.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.member.entity.Member;
import org.zerock.sony.member.entity.MemberRole;
import org.zerock.sony.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {
	private final MemberRepository repository;	// 주입
	
	@Override
	public void register(MemberDTO dto) {
		log.info(dto);
		Member member = dtoToEntity(dto);
		if(dto.getGrade()==0) {
			member.addMemberRole(MemberRole.ADMIN);
		} else {
			member.addMemberRole(MemberRole.USER);
		}
        repository.save(member);
	}

	@Override
	public MemberDTO memberLogin(String userid, String pwd) {
		log.info(userid);
		Optional<Member> result = repository.findByUserid(userid,false);
		log.info(result);
		if(result == null) {
			return null;
		} else {
			MemberDTO memberDTO = entityToDTO(result.get());
			log.info(memberDTO);
			if(memberDTO.getPwd().equals(pwd)) {
				return memberDTO;
			} else {
				return null;
			}
		}
	}

	@Override
	public MemberDTO FindMember(String userid, boolean social) {
		Optional<Member> result = repository.findByUserid(userid, social);
		if(result==null) {
			return null;
		} else {
			MemberDTO memberDTO = entityToDTO(result.get());
			return memberDTO;
		}
	}

	@Override
	public void modify(MemberDTO dto) {
		Member member = dtoToEntity(dto);
		if(dto.getGrade()==0) {
			member.addMemberRole(MemberRole.ADMIN);
		} else {
			member.addMemberRole(MemberRole.USER);
		}
        repository.save(member);
	}
}
