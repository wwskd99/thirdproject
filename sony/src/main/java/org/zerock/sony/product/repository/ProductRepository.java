package org.zerock.sony.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.sony.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("select p, pi from Product p left outer join Image pi on pi.product = p " +
			" where p.code = :code group by pi")
	List<Object[]> getProductWithAll(@Param("code")long code);
	
	@Query("select p, pi from Product p left outer join Image pi on pi.product = p " +
			"group by p")
	Page<Object[]> findAllWithImage(Pageable pageable);

}
