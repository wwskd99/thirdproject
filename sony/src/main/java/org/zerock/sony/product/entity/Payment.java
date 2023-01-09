package org.zerock.sony.product.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@ToString(exclude = {"buyer","cart","product"})
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int delivery_num;

	@OneToOne(fetch = FetchType.LAZY)
    private Member buyer;
	
	private String del_name;
	private String del_phone;
	private String del_address;
	private String del_message;
	private String del_date;
	private String paymethod;
	private String del_pass;
	private int post;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Cart> cart;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Product> product;
}
