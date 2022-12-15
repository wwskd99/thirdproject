package org.zerock.sony.member.service;

import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.member.entity.Member;

public interface MemberService {
	void register(MemberDTO dto);
	void modify(MemberDTO dto);
	MemberDTO memberLogin(String userid, String pwd);
	MemberDTO FindMember(String userid, boolean social);
	
	// DTO -> Entity
    default Member dtoToEntity(MemberDTO dto){
        Member member = Member.builder()
        		.name(dto.getName())
        		.userid(dto.getUserid())
        		.pwd(dto.getPwd())
        		.email(dto.getEmail())
        		.address(dto.getAddress())
        		.phone(dto.getPhone())
        		.grade(dto.getGrade())
        		.gender(dto.getGender())
        		.mile(dto.getMile())
        		.fromSocial(dto.isFromSocial())
        		.build();
        return member;
    }
    
    default MemberDTO entityToDTO(Member member) {
        MemberDTO memberDTO = MemberDTO.builder()
        		.name(member.getName())
        		.userid(member.getUserid())
        		.pwd(member.getPwd())
        		.email(member.getEmail())
        		.address(member.getAddress())
        		.phone(member.getPhone())
        		.grade(member.getGrade())
        		.gender(member.getGender())
        		.mile(member.getMile())
        		.fromSocial(member.isFromSocial())
        		.build();
        return memberDTO;
    }
}
