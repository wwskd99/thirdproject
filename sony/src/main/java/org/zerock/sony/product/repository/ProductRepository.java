package org.zerock.sony.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sony.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
