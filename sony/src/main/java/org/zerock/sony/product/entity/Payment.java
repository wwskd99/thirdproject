package org.zerock.sony.product.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.zerock.sony.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Payment {
	@Id
	private int delivery_num;

	@ManyToOne(fetch = FetchType.LAZY)
    private Member buyer;
	
	private String del_name;
	private String del_phone;
	private String del_address;
	private String del_message;
	private String del_date;
	private String paymethod;
	private String del_pass;
	private int post;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Cart cart;
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;
}
