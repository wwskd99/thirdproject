package org.zerock.sony.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {	
	
	private String type;
	private String keyword;
	
}
