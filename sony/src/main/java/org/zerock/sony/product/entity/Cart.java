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
public class Cart {
	@Id
	private int cart_id; 
	@ManyToOne(fetch = FetchType.LAZY)
    private Member buyer;
	@ManyToOne(fetch = FetchType.LAZY)
    private Product product;

	private int amount;
}
