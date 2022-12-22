package org.zerock.sony.product.service;

import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.entity.Product;

public interface ProductService {
	Long register(ProductDTO dto);
	
	default Product dtoToEntity(ProductDTO dto) {
		Product product = Product.builder()
				.code(dto.getCode())
				.name(dto.getName())
				.price(dto.getPrice())
				.description(dto.getDescription())
				.category_id(dto.getCategory_id())
				.category_name(dto.getCategory_name())
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
				.category_id(product.getCategory_id())
				.category_name(product.getCategory_name())
				.stock(product.getStock())
				.regDate(product.getRegDate())
        		.modDate(product.getModDate())
				.build();
		return productDTO;
	}
}
