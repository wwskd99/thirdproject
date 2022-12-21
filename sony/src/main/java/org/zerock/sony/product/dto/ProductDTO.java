package org.zerock.sony.product.dto;

import java.time.LocalDateTime;

import org.zerock.sony.product.entity.Category;

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
	private int code;
	private String name;
	private int price;
	private String pictureUrl;
	private String description;
	private Category category;
	private int stock;
	private LocalDateTime regDate, modDate;
}
