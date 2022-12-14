package org.zerock.sony.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	
	@EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.fromSocial =:social and m.userid =:userid")
    Optional<Member> findByUserid(@Param("userid") String userid, @Param("social") boolean social);

	@EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from Member m where m.fromSocial =:social and m.email =:email")
	Optional<Member> findByEmail(@Param("email") String email, @Param("social") boolean social);

}
