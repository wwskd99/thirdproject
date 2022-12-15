package org.zerock.sony.product.service;

import org.springframework.stereotype.Service;
import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.entity.Product;
import org.zerock.sony.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {
	private final ProductRepository repository;
	
	@Override
	public void register(ProductDTO dto) {
		log.info(dto);
		Product product = dtoToEntity(dto);
		repository.save(product);
	}

}
