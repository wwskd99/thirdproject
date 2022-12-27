package org.zerock.sony.product.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.common.dto.PageRequestDTO;
import org.zerock.sony.category.service.CategoryService;
import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.member.service.MemberService;
import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.entity.Category;
import org.zerock.sony.product.repository.ImageRepository;
import org.zerock.sony.product.service.ProductService;
import org.zerock.sony.security.dto.AuthMemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
@Log4j2
public class ProductController {
	private final MemberService MService;
	private final CategoryService CService;
	private final ProductService PService;
	private final ImageRepository PiService;

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
	
	@GetMapping("/list")
	public void list(@AuthenticationPrincipal AuthMemberDTO authmemberDTO, PageRequestDTO pageRequestDTO, Model model) {
		MemberDTO memberDTO = MService.FindMember(authmemberDTO.getUserid(), authmemberDTO.isFromSocial());
		model.addAttribute("member", memberDTO);
		model.addAttribute("productList", PService.getList(pageRequestDTO));
	}
	
	@GetMapping("/write")
	public void write() {

	}
	
	@PostMapping("/write")
	public String writeProduct(ProductDTO dto) {
		dto.setCategory(CService.setCategory(dto.getCategory().getId()));
		log.info(dto);
		PService.register(dto);
		return "redirect:/product/list";
	}
	
	@GetMapping("/update")
	public void update(long code, Model model) {
		ProductDTO productDTO = PService.findOneProduct(code);
		log.info(productDTO);
		model.addAttribute("product", productDTO);
	}
	@PostMapping("/update")
	public String updateProduct(ProductDTO dto) {
		dto.setCategory(CService.setCategory(dto.getCategory().getId()));
		PiService.deleteByCode(dto.getCode());
		PService.updateProduct(dto);
		return "redirect:/product/list";
	}
	@DeleteMapping("/delete")
	public String delete(long code) {
		PService.delete(code);
		return "redirect:/product/list";
	}
}