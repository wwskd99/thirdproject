package org.zerock.sony.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.sony.product.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
