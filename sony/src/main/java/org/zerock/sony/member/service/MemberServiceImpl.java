package org.zerock.sony.member.service;

import org.springframework.stereotype.Service;
import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.member.entity.Member;
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
        repository.save(member);
	}

}
