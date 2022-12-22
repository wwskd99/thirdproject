package org.zerock.sony.product.dto;

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
public class ProductDTO {
	private Long code;
	private String name;
	private int price;
	private String pictureUrl;
	private String description;
	private int category_id;
	private String category_name;
	private int stock;
	private LocalDateTime regDate, modDate;
}
