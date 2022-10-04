package kr.inhatc.capstone.rainyday.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller		// 리턴 정보를 클라이언트에 전달
public class RainydayController {

	@GetMapping(value = "rainyday/introPage")
	public String introPage() {
		return "rainyday/login/introPage";
	}
	
	@GetMapping(value = "rainyday/signUpUser")
	public String signUpUser() {
		return "rainyday/login/signUpUser";
	}
	
	@GetMapping(value = "rainyday/signUpAdmin")
	public String signUpAdmin() {
		return "rainyday/login/signUpAdmin";
	}
	
	@GetMapping(value = "rainyday/findId")
	public String findId() {
		return "rainyday/login/findId";
	}
	
	@GetMapping(value = "rainyday/findIdDetail")
	public String findIdDetail() {
		return "rainyday/login/findIdDetail";
	}
	
	@GetMapping(value = "rainyday/findPw")
	public String findPw() {
		return "rainyday/login/findPw";
	}
	
	@GetMapping(value = "rainyday/findPwDetail")
	public String findPwDetail() {
		return "rainyday/login/findPwDetail";
	}
	
	
}
