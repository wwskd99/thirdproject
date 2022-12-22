package org.zerock.sony.notice.service;

import org.zerock.common.dto.PageRequestDTO;
import org.zerock.common.dto.PageResultDTO;
import org.zerock.sony.notice.dto.NoticeDTO;
import org.zerock.sony.notice.entity.Notice;

public interface NoticeService {
	int register(NoticeDTO dto);
	NoticeDTO read(int num);
	
	PageResultDTO<NoticeDTO, Notice> getList(PageRequestDTO requestDTO);
	
	default Notice dtoToEntity(NoticeDTO dto){
		Notice notice = Notice.builder()
				.num(dto.getNum())
        		.title(dto.getTitle())
        		.content(dto.getContent())
        		.readcount(dto.getReadcount())
        		
        		.build();
        return notice;
    }
    
    default NoticeDTO entityToDTO(Notice notice) {
        NoticeDTO noticeDTO = NoticeDTO.builder()
        		.num(notice.getNum())
        		.title(notice.getTitle())
        		.content(notice.getContent())
        		.readcount(notice.getReadcount())
        		.regDate(notice.getRegDate())
        		.modDate(notice.getModDate())
        		.build();
        return noticeDTO;
    }
}
