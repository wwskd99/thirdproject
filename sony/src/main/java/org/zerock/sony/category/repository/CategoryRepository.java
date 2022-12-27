package org.zerock.sony.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sony.product.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
