package org.zerock.sony.category.service;

import org.zerock.sony.product.dto.CategoryDTO;
import org.zerock.sony.product.entity.Category;

public interface CategoryService {
	CategoryDTO setCategory(int id);
	
	default Category dtoToEntity(CategoryDTO dto){
			Category category = Category.builder()
							.id(dto.getId())
							.name(dto.getName())
							.build();
			return category;
	}
	    
	default CategoryDTO entityToDTO(Category category) {
			CategoryDTO categoryDTO = CategoryDTO.builder()
	        				.id(category.getId())
	        				.name(category.getName())
	        				.build();
			return categoryDTO;
	}
}
