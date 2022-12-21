package org.zerock.sony.product.service;

import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.entity.Product;

public interface ProductService {
	void register(ProductDTO dto);
	
	default Product dtoToEntity(ProductDTO dto) {
		Product product = Product.builder()
				.code(dto.getCode())
				.name(dto.getName())
				.price(dto.getPrice())
				.pictureUrl(dto.getPictureUrl())
				.description(dto.getDescription())
				.category(dto.getCategory())
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
				.category(product.getCategory())
				.stock(product.getStock())
				.modDate(product.getModDate())
				.regDate(product.getRegDate())
				.build();
		return productDTO;
	}
}
