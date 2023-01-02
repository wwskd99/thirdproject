package org.zerock.sony.product.repository.search;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchProductRepository {
	Page<Object[]> search(String keyword, Pageable pageable);
}
