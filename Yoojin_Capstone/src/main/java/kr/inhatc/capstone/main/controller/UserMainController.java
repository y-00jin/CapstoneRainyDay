package kr.inhatc.capstone.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller // 리턴 정보를 클라이언트에 전달
@Log4j2
@RequestMapping("/rainyday/main/user")
public class UserMainController {

    @GetMapping(value = "/userUmbrella")
    public String adminUmbrella(Model model) {
//        model.addAttribute("membersFormDto", new MembersFormDto());
//        model.addAttribute("loginCheck", "");
        return "rainyday/main/user/userUmbrella";
    }
}
