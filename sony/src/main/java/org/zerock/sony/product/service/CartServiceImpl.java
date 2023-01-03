package org.zerock.sony.product.service;


import java.util.Map;
import org.springframework.stereotype.Service;
import org.zerock.sony.product.dto.CartDTO;
import org.zerock.sony.product.entity.Cart;
import org.zerock.sony.product.repository.CartRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CartServiceImpl implements CartService {
	private final CartRepository repository;
	
	@Override
	public void insert(CartDTO cartdto) {
		
		Map<String, Object> entityMap = dtoToEntity(cartdto);
		Cart cart = (Cart) entityMap.get("cart");
		repository.save(cart);		
	}
}
