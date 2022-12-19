package org.zerock.sony.product.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.zerock.common.entity.BaseEntity;
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
public class Cart extends BaseEntity {
	@Id
	private int cart_id; 
	@ManyToOne(fetch = FetchType.LAZY)
    private Member buyer;
	private int amount;
	private int code;
	private int category_id;
	private String name;
	private int price;
	private String pictureurl;
	private String description;
	private int stock;
}
