package kr.inhatc.capstone.main.controller;

import java.lang.reflect.Member;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.inhatc.capstone.main.rental.entity.Rental;
import kr.inhatc.capstone.main.rental.service.RentalService;
import kr.inhatc.capstone.main.umbrella.service.UmbrellaService;
import kr.inhatc.capstone.members.controller.MembersController;
import kr.inhatc.capstone.utils.DataUtils;
import lombok.extern.log4j.Log4j2;

@Controller // 리턴 정보를 클라이언트에 전달
@Log4j2
@RequestMapping("/rainyday/main/user")
public class UserMainController {
    
    @Autowired
    UmbrellaService umbrellaService;
    
    @Autowired
    RentalService rentalService;
    
    
    private List<Rental> rentalList;
    
    @GetMapping(value = "/userUmbrella")
    public String userUmbrella(Model model) {
        
        int countAll = umbrellaService.countUmAll();

        int countRentalY = umbrellaService.countUmRentallY();
        
        String result = countRentalY + " / " + countAll;
        String Remain = (countAll - countRentalY) + "";
        
        model.addAttribute("result", result);
        model.addAttribute("Remain", Remain);

        return "rainyday/main/user/userUmbrella";
    }
    
    @GetMapping(value = "/userRental")
    public String userRental(Model model) {

        System.out.println("**************"+ DataUtils.getStaticMemberDep());
        rentalList = rentalService.myRental(DataUtils.getStaticMemberDep());
        model.addAttribute("rentalList", rentalList);
        
        if(rentalList == null) {
            model.addAttribute("btnExtension", "false");
        }
        else {
            model.addAttribute("btnExtension", "true");
        }
        
        
        return "rainyday/main/user/userRental";
    }
    
    @GetMapping(value = "/userNotice")
    public String userNotice(Model model) {
//        model.addAttribute("membersFormDto", new MembersFormDto());
//        model.addAttribute("loginCheck", "");
        return "rainyday/main/user/userNotice";
    }
}
