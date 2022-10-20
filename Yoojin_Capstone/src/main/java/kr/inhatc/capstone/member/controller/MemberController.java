//package kr.inhatc.capstone.member.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import kr.inhatc.capstone.member.dto.MemberFormDto;
//import kr.inhatc.capstone.member.entity.Member;
//import kr.inhatc.capstone.member.service.MemberService;
//import lombok.extern.log4j.Log4j2;
//
//
//@Controller		// 리턴 정보를 클라이언트에 전달
//@Log4j2
//@RequestMapping("/rainyday")
//public class MemberController {
//
//	@Autowired
//    MemberService memberService;
//    
//    @Autowired
//    PasswordEncoder passwordEncoder;
//    
//    
//	@GetMapping(value = "/introPage")
//	public String introPage() {
//		return "rainyday/login/introPage";
//	}
//	
//	@GetMapping(value = "/signUpUser")
//	public String signUpUser() {
//		return "rainyday/login/signUpUser";
//	}
//	
//	@GetMapping(value = "/signUpAdmin")
//	public String signUpAdmin(Model model) {
//		model.addAttribute("memberFormDto", new MemberFormDto());
//		return "rainyday/login/signUpAdmin";
//	}
//	
//	 @PostMapping(value = "/signUpAdmin")
//	    public String memberForm(MemberFormDto memberFormDto) {
//	        log.info("===============================> " + memberFormDto);
//	        
//	        Member member = Member.createMrmberAdmin(memberFormDto, passwordEncoder);
//	        memberService.saveMember(member);
//	        
//	        // 들고온 memberFormDto로 get set해서 DB에 넣어줌
//	        
//	        
//	        return "redirect:/";
//	    }
//	
//	@GetMapping(value = "/findId")
//	public String findId() {
//		return "rainyday/login/findId";
//	}
//	
//	@GetMapping(value = "/findIdDetail")
//	public String findIdDetail() {
//		return "rainyday/login/findIdDetail";
//	}
//	
//	@GetMapping(value = "/findPw")
//	public String findPw() {
//		return "rainyday/login/findPw";
//	}
//	
//	@GetMapping(value = "/findPwDetail")
//	public String findPwDetail() {
//		return "rainyday/login/findPwDetail";
//	}
//	
//	
//}
