package org.zerock.sony.member.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.member.entity.Member;
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
	private final PasswordEncoder passwordEncoder;
    
	@GetMapping("/login")
	public void login(){

	}
	
	@GetMapping("/join")
	public void join() {
		
	}
	
	@PostMapping("/join")
	public String joinMember(MemberDTO dto, RedirectAttributes rttr) {
		log.info(dto);
		dto.setGrade(1);
		dto.setPwd(passwordEncoder.encode(dto.getPwd()));

		MService.register(dto);
		rttr.addFlashAttribute("kind", "reg");
		rttr.addFlashAttribute("msg", "회원가입 완료");
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
	public void modify(@AuthenticationPrincipal AuthMemberDTO authmemberDTO, Model model) {
		MemberDTO memberDTO = MService.FindMember(authmemberDTO.getUserid(), authmemberDTO.isFromSocial());
		model.addAttribute("member", memberDTO);
	}
	
	@PostMapping("/modify")
	public String modifyMember(MemberDTO dto, RedirectAttributes rttr, Model model, String pwd_check) {
		log.info(dto);
		if(!dto.getPwd().equals(pwd_check)) {
			rttr.addFlashAttribute("msg", "암호가 같지 않습니다.");
			model.addAttribute("member", dto);
			return "redirect:/member/modify";
		}
		dto.setPwd(passwordEncoder.encode(dto.getPwd()));
		MService.modify(dto);
		return "redirect:/logout";
	}
}