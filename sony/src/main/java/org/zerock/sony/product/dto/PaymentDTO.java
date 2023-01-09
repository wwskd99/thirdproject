package org.zerock.sony.product.dto;

import java.util.List;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.member.entity.Member;
import org.zerock.sony.product.entity.Cart;
import org.zerock.sony.product.entity.Product;

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
public class PaymentDTO {
	private int delivery_num;

    private MemberDTO buyer;
	
	private String del_name;
	private String del_phone;
	private String del_address;
	private String del_message;
	private String del_date;
	private String paymethod;
	private String del_pass;
	private int post;
	
	private List<CartDTO> cart;
	private List<ProductDTO> product;
}
