package org.zerock.sony.product.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.entity.Product;
import org.zerock.sony.product.entity.QProduct;
import org.zerock.sony.product.repository.ProductRepository;
import org.zerock.sony.product.search.ProductSearch;

import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
	private final ProductRepository repository;

	@Override
	public Long register(ProductDTO dto) {
		log.info("DTO..");
		log.info(dto);
		Product product = dtoToEntity(dto);
		repository.save(product);
		return product.getCode();
	}

}
