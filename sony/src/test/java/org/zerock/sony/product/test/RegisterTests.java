package org.zerock.sony.product.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.service.ProductService;

@SpringBootTest
public class RegisterTests {
	@Autowired
	private ProductService productService;
	
	@Test
	public void testRegister(){
		ProductDTO dto = ProductDTO.builder()
				.name("test..")
				.price(1000000)
				.description("desc..")
				.name("좋은카메라")
				.stock(50)
				.build();
		Long code = productService.register(dto);
	}
}
