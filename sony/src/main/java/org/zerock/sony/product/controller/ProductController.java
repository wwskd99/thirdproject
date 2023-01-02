package org.zerock.sony.product.controller;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.entity.Product;
import org.zerock.sony.product.search.ProductSearch;
import org.zerock.sony.product.service.ProductService;

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
	
	@GetMapping("/register") 
	 public void register(Model model){
	 log.info("regiser get...");
	 model.addAttribute("productlist", productlist);

	 }
	

	@RequestMapping("/getSearchlist")
	public String searchlist(Model model, ProductSearch search) {
       
		if(search.getKeyword() == null)
				search.setKeyword("");
		Page<Product> productlist = ProductService.getSearchlist(search);
		model.addAttribute("productlist", productlist);
		return "product/searchlist";
	}
	
}