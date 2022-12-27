package org.zerock.sony.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.sony.product.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	@Transactional
	@Modifying
	@Query("delete from Image i where i.product.code =:code ")
    void deleteByCode(@Param("code") long code);
	
}