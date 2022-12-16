package org.zerock.sony.notice.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.common.dto.PageRequestDTO;
import org.zerock.common.dto.PageResultDTO;
import org.zerock.sony.notice.dto.NoticeDTO;
import org.zerock.sony.notice.entity.Notice;
import org.zerock.sony.notice.entity.QNotice;
import org.zerock.sony.notice.repository.NoticeRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeRepository repository;
	
	@Override
	public int register(NoticeDTO dto) {
		Notice entity = dtoToEntity(dto);
		log.info(entity);
		repository.save(entity);
		return entity.getNum();
	}

	@Override
	public PageResultDTO<NoticeDTO, Notice> getList(PageRequestDTO requestDTO) {
		Pageable pageable = requestDTO.getPageable(Sort.by("num").descending());
		BooleanBuilder booleanBuilder = getSearch(requestDTO);
		Page<Notice> result = repository.findAll(booleanBuilder, pageable);
		// Page<Guestbook> -> PageResultDTO<GuestbookDTO, Guestbook>
		// entity->dto 변환하는 함수 -> 람다식
		Function<Notice, NoticeDTO> fn = (entity -> entityToDTO(entity));
		return new PageResultDTO<>(result, fn);
	}
	
	private BooleanBuilder getSearch(PageRequestDTO requestDTO) {
		String type = requestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QNotice qNotice = QNotice.notice;
        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qNotice.num.gt(0L); // num > 0 조건만 생성
        booleanBuilder.and(expression);
        if(type == null || type.trim().length() == 0){ //검색 조건이 없는 경우
            return booleanBuilder;
        }
        //검색 조건을 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("t")){
            conditionBuilder.or(qNotice.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qNotice.content.contains(keyword));
        }
        //모든 조건 통합
        booleanBuilder.and(conditionBuilder);
        return booleanBuilder;
	}

	@Override
	public NoticeDTO read(int num) {
		repository.updateCount(num);
		Optional<Notice> result = repository.findById(num);
		return (result.isPresent()? entityToDTO(result.get()) : null);
	}

}
