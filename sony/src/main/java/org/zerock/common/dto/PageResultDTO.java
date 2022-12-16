package org.zerock.common.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

// 화면 처리를 위한 클래스 DTO
// 데이터베이스 검색 결과로부터 만들어진다.

@Data
public class PageResultDTO<DTO, EN> {
	private List<DTO> dtoList;
	private int totalPage;		// 총 페이지 번호
	private int page;			// 현재 페이지 번호
	private int size;			// 목록 크기 : 페이지당 갯수
	private int start, end;		// 시작 페이지, 마지막 페이지
	private boolean prev, next;	// 이전, 다음 여부
	private List<Integer> pageList;	// 페이지 번호 목록
	
	// 생성자
	// 매개변수 Page<EN> 데이터베이스 검색 결과 : 예, Page<Guestbook>
	// Function<EN, DTO> fn : Entity -> DTO로 변환하는 함수 -> Service에서 정의됨
	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		totalPage = result.getTotalPages();
		makePageList(result.getPageable());
	}

	private void makePageList(Pageable pageable) {
		this.page = pageable.getPageNumber() + 1;	// 0부터 시작하므로 1을 추가
        this.size = pageable.getPageSize();
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;	//temp end page
        start = tempEnd - 9;
        prev = start > 1;
        end = totalPage > tempEnd ? tempEnd: totalPage;
        next = totalPage > tempEnd;
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
	}
}