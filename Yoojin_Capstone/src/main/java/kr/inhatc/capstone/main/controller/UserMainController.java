package kr.inhatc.capstone.main.controller;

import java.lang.reflect.Member;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.inhatc.capstone.main.board.entity.Board;
import kr.inhatc.capstone.main.board.service.BoardService;
import kr.inhatc.capstone.main.rental.dto.RentalFormDto;
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
    
    @Autowired
    BoardService boardService;
    
    private List<Rental> rentalList;
    
    @GetMapping(value = "/userUmbrella")
    public String userUmbrella(Model model) {
        
        DataUtils.setStaticMemberDep(DataUtils.getStaticMemberDep());
        
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
        
        DataUtils.setStaticMemberDep(DataUtils.getStaticMemberDep());

        rentalList = rentalService.myRental(DataUtils.getStaticMemberDep());
        
        if(rentalList.size() != 0) {
            boolean extensionCheck = rentalService.extensionCheck(DataUtils.getStaticMemberDep());
        
            if(extensionCheck == true) {        // 연장 한 경우
                model.addAttribute("btnExtension", false);
            }
            else {                              // 연장 X
                model.addAttribute("btnExtension", true);
            }
            
        }else {
            model.addAttribute("btnExtension", false);
        }
        
        
        model.addAttribute("rentalList", rentalList);
        return "rainyday/main/user/userRental";
    }
    
    @PostMapping(value = "/userRental")
    public String userRental(Model model, RentalFormDto rentalFormDto) {
        
        DataUtils.setStaticMemberDep(DataUtils.getStaticMemberDep());

        rentalList = rentalService.myRental(DataUtils.getStaticMemberDep());
        
        
        if(rentalList.size() != 0) {
            model.addAttribute("btnExtension", true);
            // 수정
            rentalService.updateReturnDate(rentalList.get(0).getReturnDate());
            
            
            rentalList = rentalService.myRental(DataUtils.getStaticMemberDep());
            
            
        }
        else {
            model.addAttribute("btnExtension", false);
        }
        
        
        model.addAttribute("rentalList", rentalList);
        model.addAttribute("rentalFormDto", rentalFormDto);
        
        return "redirect:/rainyday/main/user/userRental";
    }
    
    
    @GetMapping(value = "/userNotice")
    public String userNotice(Model model) {
        
        DataUtils.setStaticMemberDep(DataUtils.getStaticMemberDep());
        
        List<Board> boardList = boardService.findAllBoard();
        model.addAttribute("boardList", boardList);
//        model.addAttribute("membersFormDto", new MembersFormDto());
//        model.addAttribute("loginCheck", "");
        return "rainyday/main/user/userNotice";
    }
}
