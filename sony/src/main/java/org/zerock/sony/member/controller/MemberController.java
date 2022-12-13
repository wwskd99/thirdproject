package org.zerock.sony.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {
	private final MemberService MService;
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@PostMapping("/login")
	public String loginMember(MemberDTO dto, RedirectAttributes rttr, HttpServletRequest req) {
		MemberDTO member = MService.memberLogin(dto.getUserid(), dto.getPwd());
		if(member == null) {
			rttr.addFlashAttribute("message", "fail");
			return "redirect:/member/login";
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", member);
			return "redirect:/main/home";
		}
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
}