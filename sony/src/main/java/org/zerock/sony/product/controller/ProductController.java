package org.zerock.sony.product.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.sony.product.dto.ProductDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

	@GetMapping("/camera")
	public void camera() {
	
	}

	@GetMapping("/videocamera")
	public void videocamera() {

	}
	
	@GetMapping("/audio")
	public void audio() {

	}
	
	@GetMapping("/accessory")
	public void accessory() {

	}
	
	
	@GetMapping("/cart")
	public void cart() {

	}
	
}