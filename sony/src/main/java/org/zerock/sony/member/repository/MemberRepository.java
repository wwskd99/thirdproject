package org.zerock.sony.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sony.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
}
