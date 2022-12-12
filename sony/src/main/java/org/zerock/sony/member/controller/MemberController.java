package org.zerock.sony.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.sony.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {
	private final MemberService MService;
	
	@RequestMapping("/login")
	public void login() {
		
	}
	
	@GetMapping("/join")
	public void join() {
		
	}
	
	@PostMapping("/join")
	public String joinMember() {
		return "redirect:/member/login";
	}
	
	@GetMapping("/findpwd")
	public void findpwd() {
		
	}
}