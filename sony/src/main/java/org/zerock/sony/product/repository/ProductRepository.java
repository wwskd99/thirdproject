package org.zerock.sony.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.entity.Product;
import org.zerock.sony.search.repository.SearchProductRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, SearchProductRepository {

	static List<ProductDTO> findAllSearch(String keyword) {

		return null;
	}

}
