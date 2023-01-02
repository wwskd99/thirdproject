package org.zerock.sony.product.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.entity.Category;
import org.zerock.sony.product.entity.Product;
import org.zerock.sony.product.entity.QProduct;
import org.zerock.sony.product.repository.ProductRepository;
import org.zerock.sony.product.search.ProductSearch;

public interface ProductService {
	Long register(ProductDTO dto);

	default Product dtoToEntity(ProductDTO dto) {
		Category category = Category.builder().id(dto.getCategory()).build();
		
		Product product = Product.builder()
				.code(dto.getCode())
				.name(dto.getName())
				.price(dto.getPrice())
				.description(dto.getDescription())
				.category(category)
				.stock(dto.getStock())
				.build();
		return product;
	}

	default ProductDTO entityToDTO(Product product) {
		ProductDTO productDTO = ProductDTO.builder()
				.code(product.getCode())
				.name(product.getName())
				.price(product.getPrice())
				.pictureUrl(product.getPictureUrl())
				.description(product.getDescription())
				.stock(product.getStock())
				.regDate(product.getRegDate())
				.modDate(product.getModDate())
				.build();
		return productDTO;
	}
	
	Page<Product> getSearchlist(ProductSearch search);

}
