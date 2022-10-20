package kr.inhatc.capstone.members.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.inhatc.capstone.members.dto.MembersFormDto;
import kr.inhatc.capstone.members.entity.Members;
import kr.inhatc.capstone.members.service.MembersService;
import lombok.extern.log4j.Log4j2;

@Controller		// 리턴 정보를 클라이언트에 전달
@Log4j2
@RequestMapping("/rainyday/members")
public class MembersController {

	@Autowired
	MembersService membersService;
	
  @Autowired
  PasswordEncoder passwordEncoder;
	
	/**
	 * 시작 화면 introPage
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/introPage")
	public String introPage(Model model) {
		model.addAttribute("membersFormDto", new MembersFormDto());
		model.addAttribute("loginCheck", "");
		return "rainyday/login/introPage";
	}
	
	/**
	 * introPage에서 submit 클릭 -> DB에서 아이디, 비밀번호 조회
	 * @param membersFormDto
	 * @return
	 */
	@PostMapping(value = "/introPage")
	public String introPage(MembersFormDto membersFormDto, Model model) {
		
		
		
		Members members = Members.createMembers(membersFormDto);	// 웹에서 submit해서 받은 정보로 member 객체 만들기
		
		System.out.println("=========================> 웹에서 받은 멤버로 만든 객체 " + members);
		
		boolean loginCheck = membersService.loginMember(members);
		
		System.out.println("=========================> 로그인 체크 " + loginCheck);
		
		if(loginCheck == true) {	// 로그인 성공
			return "redirect:/";
		}
		else {
			model.addAttribute("loginCheck", "아이디 및 비밀번호를 확인하세요");
			return "rainyday/login/introPage";	// 로그인 실패
		}
	}
	
	
}
