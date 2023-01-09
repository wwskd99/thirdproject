package org.zerock.sony.product.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerock.sony.product.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query("SELECT c, b from Cart c left Outer join c.buyer b where b.userid = :userid")
	List<Object[]> getCartWithBuyer(@Param("userid") String userid);
	
	@Query(value = "SELECT c, b, p" +
			" FROM Cart c " +
			" LEFT OUTER JOIN c.buyer b " +
			" LEFT OUTER JOIN c.product p " +
			" GROUP BY c ",
			countQuery = "SELECT count(c) FROM Cart c")
		Page<Object[]> getCartWithList(Pageable pageable);	
	
}
