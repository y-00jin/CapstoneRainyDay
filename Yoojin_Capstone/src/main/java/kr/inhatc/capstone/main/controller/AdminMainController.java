package kr.inhatc.capstone.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller // 리턴 정보를 클라이언트에 전달
@Log4j2
@RequestMapping("/rainyday/main/admin")
public class AdminMainController {
    
    
    @GetMapping(value = "/adminUmbrella")
    public String adminUmbrella(Model model) {
//        model.addAttribute("membersFormDto", new MembersFormDto());
//        model.addAttribute("loginCheck", "");
        System.out.println("fffffffffffffffffffffffffffff");
        return "rainyday/main/admin/adminUmbrella";
    }
    
    @GetMapping(value = "/adminStudent")
    public String adminStudent(Model model) {
//        model.addAttribute("membersFormDto", new MembersFormDto());
//        model.addAttribute("loginCheck", "");
        System.out.println("dddddddddddddddddddddddddddddddddddddddd");
        return "rainyday/main/admin/adminStudent";
    }

    @GetMapping(value = "/adminRental")
    public String adminRental(Model model) {
//        model.addAttribute("membersFormDto", new MembersFormDto());
//        model.addAttribute("loginCheck", "");
        System.out.println("eeeeeeeeeeeeeee");
        return "rainyday/main/admin/adminRental";
    }
    
    @GetMapping(value = "/adminNotice")
    public String adminNotice(Model model) {
//        model.addAttribute("membersFormDto", new MembersFormDto());
//        model.addAttribute("loginCheck", "");
        System.out.println("qqqqqqq");
        return "rainyday/main/admin/adminNotice";
    }
}
