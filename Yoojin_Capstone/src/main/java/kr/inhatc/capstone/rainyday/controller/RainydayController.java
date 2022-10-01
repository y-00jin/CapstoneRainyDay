package kr.inhatc.capstone.rainyday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller		// 리턴 정보를 클라이언트에 전달
public class RainydayController {

	@GetMapping(value = "rainyday/introPage")
	public String introPage() {
		return "rainyday/login/introPage";
	}
	
	@GetMapping(value = "rainyday/signUp")
	public String signUp() {
		return "rainyday/login/signUp";
	}
	
}
