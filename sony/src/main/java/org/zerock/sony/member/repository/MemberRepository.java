package org.zerock.sony.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	public Member findByUserid(String userid);
}
