package org.zerock.common.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// 페이징 + 검색조건
@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {	// 스프링에서 했던 Criteria와 유사
	// 필드 : 페이징, 검색조건
	private int page;	// 현재 페이지
	private int size;	// 1페이지당 갯수
	private String type;
	private String keyword;
	
	public PageRequestDTO() {
		this.page = 1;
		this.size = 10;
	}
	
	// Pageable 객체에 대한 getter를 정의
	public Pageable getPageable(Sort sort) {
		// page는 0부터 시작하므로 -1 : 1 페이지 -> 1-1=0
		return PageRequest.of(page - 1, size, sort);
	}
}
