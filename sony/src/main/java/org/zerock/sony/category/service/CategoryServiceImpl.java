package org.zerock.sony.category.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.zerock.sony.category.repository.CategoryRepository;
import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.product.dto.CategoryDTO;
import org.zerock.sony.product.entity.Category;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository repository;

	@Override
	public CategoryDTO setCategory(int id) {
		Optional<Category> result = repository.findById(id);
		if(result == null) {
			return null;
		} else {
			CategoryDTO categoryDTO = entityToDTO(result.get());
			return categoryDTO;
		}
	}
}
