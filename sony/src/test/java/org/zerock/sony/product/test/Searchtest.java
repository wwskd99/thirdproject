package org.zerock.sony.product.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.sony.product.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class Searchtest {
	@Autowired
	private ProductRepository productRepository;

	@Test
	 public void testSearch1() {
	productRepository.search1();
	}	
}
