package org.zerock.sony.notice.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.sony.member.dto.MemberDTO;
import org.zerock.sony.member.service.MemberService;
import org.zerock.sony.notice.dto.NoticeDTO;
import org.zerock.sony.notice.service.NoticeService;
import org.zerock.sony.security.dto.AuthMemberDTO;
import org.zerock.common.dto.PageRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
@Log4j2
public class NoticeController {
	private final MemberService MService;
	private final NoticeService NService;
	
	@GetMapping("/list")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		model.addAttribute("result", NService.getList(pageRequestDTO));
	}
	
	@GetMapping("/write")
	public void write(PageRequestDTO pageRequestDTO) {
		
	}
	
	@PostMapping("/write")
	public String writeNotice(NoticeDTO noticeDTO) {
		log.info(noticeDTO);
		NService.register(noticeDTO);
		return "redirect:/notice/list";
	}
	
	@GetMapping("/read")
	public void read(int num, Model model, @AuthenticationPrincipal AuthMemberDTO authmemberDTO, PageRequestDTO pageRequestDTO) {
		if(authmemberDTO != null) {
			MemberDTO memberDTO = MService.FindMemberWithSocial(authmemberDTO.getUserid(), authmemberDTO.isFromSocial());
			model.addAttribute("member", memberDTO);
		}
		
		NoticeDTO dto = NService.read(num);
		model.addAttribute("notice", dto);		
	}
	
	@GetMapping("/modify")
	public void modify(int num, Model model, PageRequestDTO pageRequestDTO) {
		NoticeDTO dto = NService.read(num);
		log.info(dto);
		model.addAttribute("notice", dto);
	}
	@PostMapping("/modify")
	public String modifyNotice(NoticeDTO noticeDTO) {
		log.info(noticeDTO);
		NService.register(noticeDTO);
		return "redirect:/notice/list";
	}
}
