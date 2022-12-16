package org.zerock.sony.notice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.sony.notice.entity.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Integer>, QuerydslPredicateExecutor<Notice>{
	@Transactional 
	@Modifying
	 @Query("update Notice n set n.readcount = n.readcount + 1 where n.num = :num")
	 int updateCount(@Param("num") int num);
}
