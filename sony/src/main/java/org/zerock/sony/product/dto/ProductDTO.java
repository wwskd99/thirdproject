package org.zerock.sony.product.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
public class ProductDTO {
	private long code;
	private String name;
	private int price;
	
	@Builder.Default
    private List<ImageDTO> imageDTOList = new ArrayList<>();
	
	private String description;
	private CategoryDTO category;
	private int stock;
	private LocalDateTime regDate, modDate;
}
