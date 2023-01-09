package org.zerock.sony.product.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.common.dto.PageRequestDTO;
import org.zerock.sony.category.service.CategoryService;
import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.member.service.MemberService;
import org.zerock.sony.product.dto.CartDTO;
import org.zerock.sony.product.dto.ProductDTO;
import org.zerock.sony.product.repository.ImageRepository;
import org.zerock.sony.product.service.CartService;
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
	private final CartService cartService;

	@GetMapping("/cus_list")
	public void camera(PageRequestDTO pageRequestDTO, Model model, int category) {
		model.addAttribute("category", category);
		model.addAttribute("result",PService.sortNew(pageRequestDTO,category));
	}
	
	@PostMapping("/cus_list")
	public void sort(PageRequestDTO pageRequestDTO, Model model, int sort, int category) {
		log.info(category);
		model.addAttribute("category", category);
		//1:high
		if(sort==1) {
			model.addAttribute("result",PService.sortHigh(pageRequestDTO,category));
		}
		//2:low
		else if(sort==2) {
			model.addAttribute("result",PService.sortLow(pageRequestDTO,category));
		}
		//3:new
		else {
			model.addAttribute("result",PService.sortNew(pageRequestDTO,category));
		}
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
	
	@GetMapping("/view")
	public void view(long code, Model model) {
		model.addAttribute("product", PService.findOneProduct(code));
	}
	
	@GetMapping("/cart")
	 public void cart(@AuthenticationPrincipal AuthMemberDTO authmemberDTO, CartDTO cartDTOList, Model model){
        log.info("cartlist..." + cartDTOList);
        cartService.cartDTO(authmemberDTO.getUserid());
        model.addAttribute("cartList",cartService.cartDTO(authmemberDTO.getUserid()));
       
	}
	
	@PostMapping("/cart")
	public void cartPush(@AuthenticationPrincipal AuthMemberDTO authmemberDTO, long code, int amount, Model model) {
		CartDTO cartDTO = new CartDTO();
		cartDTO.setAmount(amount);
		cartDTO.setBuyer(MService.FindMember(authmemberDTO.getUserid(), authmemberDTO.isFromSocial()));
		cartDTO.setProduct(PService.findOneProduct(code));
	}
	
	@GetMapping("/search")
	public void search(PageRequestDTO pageRequestDTO, Model model) {
		model.addAttribute("result", PService.getListWithKeyword(pageRequestDTO));
	}
}