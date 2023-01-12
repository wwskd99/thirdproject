package org.zerock.sony.product.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.sony.product.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	@Query("SELECT c, b, i from Cart c left Outer join c.buyer b, Image i where b.userid = :userid and i.product = c.product group by c")
	List<Object[]> getCartWithBuyer(@Param("userid") String userid);
	
	@Query("SELECT c, b, i from Cart c left Outer join c.buyer b, Image i where c.cart_id = :cart_id and i.product = c.product")
	List<Object[]> getCartWithId(@Param("cart_id") long cart_id);
	
	@Query(value = "SELECT c, b, p" +
			" FROM Cart c " +
			" LEFT OUTER JOIN c.buyer b " +
			" LEFT OUTER JOIN c.product p " +
			" GROUP BY c ",
			countQuery = "SELECT count(c) FROM Cart c")
	Page<Object[]> getCartWithList(Pageable pageable);
		
	@Transactional
	@Modifying
	@Query(value = "delete from payment_cart where payment_cart.cart_cart_id = cart_cart_id", nativeQuery = true)
	void deletePayment_cart(long cart_cart_id);
	
	@Transactional
	@Modifying
	@Query(value = "delete from payment_product where payment_product.payment_delivery_num = payment_delivery_num", nativeQuery = true)
	void deletePayment_product(int payment_delivery_num);	
	
	@Transactional
	@Modifying
	@Query(value = "delete from cart where cart.buyer_userid is null", nativeQuery = true)
	void deleteNull();	
}
