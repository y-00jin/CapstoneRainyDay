package kr.inhatc.capstone.main.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller // 리턴 정보를 클라이언트에 전달
@Log4j2
//@RequestMapping("/rainyday/main/admin/board")
public class BoardController {
    
    
    @GetMapping(value = "/rainyday/main/admin/board/write")
    public String adminBoardWrite(Model model) {


        
        
        return "rainyday/main/admin/board/boardWrite";
    }
    
    @GetMapping(value = "/rainyday/main/admin/board/update")
    public String adminBoardUpdate(Model model) {


        
        
        return "rainyday/main/admin/board/boardUpdate";
    }
    
    
    @GetMapping(value = "/rainyday/main/board/detail")
    public String adminBoardDetail(Model model) {


        
        
        return "rainyday/main/admin/board/boardDetail";
    }

}
