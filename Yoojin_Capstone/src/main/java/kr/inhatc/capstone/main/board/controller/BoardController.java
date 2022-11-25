package kr.inhatc.capstone.main.board.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.inhatc.capstone.main.board.dto.BoardFormDto;
import kr.inhatc.capstone.main.board.entity.Board;
import kr.inhatc.capstone.main.board.service.BoardService;
import kr.inhatc.capstone.members.dto.MembersFormDto;
import kr.inhatc.capstone.members.entity.Members;
import kr.inhatc.capstone.members.service.MembersService;
import kr.inhatc.capstone.utils.DataUtils;
import lombok.extern.log4j.Log4j2;

@Controller // 리턴 정보를 클라이언트에 전달
@Log4j2
//@RequestMapping("/rainyday/main/admin/board")
public class BoardController {
    
    
    @Autowired
    MembersService membersService;
    
    @Autowired
    BoardService boardService;
    
    private boolean adminCheck = false;
    

    /**
     * 공지 작성
     * @param model
     * @return
     */
    @GetMapping(value = "/rainyday/main/board/write")
    public String adminBoardWrite(Model model) {
        
        DataUtils.setStaticMemberDep(DataUtils.getStaticMemberDep());
        
        model.addAttribute("boardFormDto", new BoardFormDto());

        return "rainyday/main/board/boardWrite";
    }
    
    /**
     * 공지 작성 post
     * @param model
     * @param boardFormDto
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/rainyday/main/board/write")
    public String adminBoardWrite(@Valid BoardFormDto boardFormDto, BindingResult bindingResult, Model model) {
        
        DataUtils.setStaticMemberDep(DataUtils.getStaticMemberDep());
        
        model.addAttribute("boardFormDto", boardFormDto);
        
        if (bindingResult.hasErrors()) { // 형식이 안 맞거나 정보를 안넣음 오류
            return "rainyday/main/board/boardWrite";
        }
        
        
        Board board = Board.createBoardNoId(boardFormDto);
        boardService.boardWrite(board);
        
        return "redirect:/rainyday/main/admin/adminNotice";
    }
    
    @GetMapping(value = "/rainyday/main/board/update/{boardId}")
    public String adminBoardUpdate(@PathVariable("boardId")Long boardId, Model model) {

        DataUtils.setStaticMemberDep(DataUtils.getStaticMemberDep());
        
        model.addAttribute("errorTitle", "");
        model.addAttribute("errorContent", "");
        
        Board board = boardService.findBoard(boardId);
        
        BoardFormDto boardFormDto = new BoardFormDto();
        boardFormDto.setId(board.getId());
        boardFormDto.setTitle(board.getTitle());
        boardFormDto.setContent(board.getContent());
        
        model.addAttribute("boardFormDto", boardFormDto);
        
        
        
        return "rainyday/main/board/boardUpdate";
    }
    
    @PostMapping(value = "/rainyday/main/board/update/{boardId}")
    public String adminBoardUpdate(@PathVariable("boardId")Long boardId, Model model, BoardFormDto boardFormDto) {

        DataUtils.setStaticMemberDep(DataUtils.getStaticMemberDep());
        
        
      if(boardFormDto.getTitle() == null || boardFormDto.getTitle().equals("") || boardFormDto.getContent() ==null || boardFormDto.getContent().equals("")) {
          model.addAttribute("errorTitle", "제목은 필수 항목 입니다.");
          model.addAttribute("errorContent", "내용은 필수 항목 입니다.");
          return "rainyday/main/board/boardUpdate";
      }
      else {
          Board board = Board.createBoard(boardFormDto);
          
          boardService.boardUpdate(board);
          
          return "redirect:/rainyday/main/board/detail/"+boardFormDto.getId();
      }
    }
    
    
    @GetMapping(value = "/rainyday/main/board/detail/{boardId}")
    public String adminBoardDetail(@PathVariable("boardId")Long boardId, Model model) {
        
        DataUtils.setStaticMemberDep(DataUtils.getStaticMemberDep());
        
        System.out.println("**************" + DataUtils.getStaticMemberDep());
        
        adminCheck = membersService.MembersAdminCheck(DataUtils.getStaticMemberDep());

        if(adminCheck == true) {        // 관리자
            model.addAttribute("adminCheck", true);
        }
        else {                          // 사용자
            model.addAttribute("adminCheck", false);
        }
        
        Board board = boardService.findBoard(boardId);
        
        model.addAttribute("boardId", board.getId());
        model.addAttribute("boardTitle", board.getTitle());
        model.addAttribute("boardCreateDate", board.getCreateDate());
        model.addAttribute("boardContent", board.getContent());
        
        return "rainyday/main/board/boardDetail";
    }
    
    
    @GetMapping(value = "/rainyday/main/board/delete/{boardId}")
    public String adminBoardDelete(@PathVariable("boardId")Long boardId, Model model, BoardFormDto boardFormDto) {
        
        DataUtils.setStaticMemberDep(DataUtils.getStaticMemberDep());
        
        boardService.boardDelete(boardId);
        
        return "redirect:/rainyday/main/admin/adminNotice";
      
    }

}
