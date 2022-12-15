package org.zerock.sony.member.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.member.service.MemberService;
import org.zerock.sony.security.dto.AuthMemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {
	private final MemberService MService;
    
	@GetMapping("/login")
	public void login(){

	}
	
	@GetMapping("/join")
	public void join() {
		
	}
	
	@PostMapping("/join")
	public String joinMember(MemberDTO dto, RedirectAttributes rttr) {
		log.info(dto);
		MService.register(dto);
		String msg = dto.getUserid();
		rttr.addFlashAttribute("kind", "reg");
		rttr.addFlashAttribute("msg", msg);
		return "redirect:/member/login";
	}
	
	@GetMapping("/findpwd")
	public void findpwd() {
		
	}
	
	@GetMapping("/loginPage")
	public void loginPage(@AuthenticationPrincipal AuthMemberDTO authmemberDTO, Model model) {
		log.info(authmemberDTO);
		 model.addAttribute("member", authmemberDTO);
	}
	
	@GetMapping("/modify")
	public void modify() {
		
	}
}