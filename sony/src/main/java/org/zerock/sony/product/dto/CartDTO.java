package org.zerock.sony.product.dto;


import org.zerock.sony.member.dto.MemberDTO;

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
public class CartDTO {
	
	private long cart_id; 
    private MemberDTO buyer;
    private ProductDTO product;

	private int amount;
}
