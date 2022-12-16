package org.zerock.sony.notice.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
	private int num;
	private String title;
	private String content;
	private int readcount;
	private LocalDateTime regDate, modDate;
}
