package org.zerock.sony.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Log4j2
public class HomeController {
	
	@GetMapping("/")
	public String home() {
		return "redirect:/main/home";
	}
}