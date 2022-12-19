package org.zerock.sony.member.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.zerock.sony.product.entity.Payment;

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
public class BuyList {
	@Id
	private int List_id;
	@ManyToOne(fetch = FetchType.LAZY)
	private Payment payment;	
}
