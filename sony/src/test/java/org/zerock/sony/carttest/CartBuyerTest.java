package org.zerock.sony.carttest;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zerock.sony.product.repository.CartRepository;

@SpringBootTest
public class CartBuyerTest {
	
	@Autowired
	CartRepository cartRepository;
	
	@Test
	public void testBuyer() {
		Pageable pageable = PageRequest.of(0 , 10);
		Page<Object[]> result = cartRepository.getCartWithList(pageable);
//		
//		Object[] arr = (Object[]) result.getContent();
		
		System.out.println("------");
//		System.out.println(Arrays.toString(arr));
	}
}
