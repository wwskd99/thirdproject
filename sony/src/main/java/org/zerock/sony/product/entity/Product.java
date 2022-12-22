package org.zerock.sony.product.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

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
public class Product extends BaseEntity{
	@Id
	private Long code;
	private String name;
	private int price;
	private String pictureUrl;
	private String description;
	private int category_id;
	private String category_name;
	private int stock;
}
