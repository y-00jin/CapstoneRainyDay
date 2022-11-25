package kr.inhatc.capstone.main.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.capstone.main.board.entity.Board;
import kr.inhatc.capstone.main.board.repository.BoardRepository;
import kr.inhatc.capstone.main.umbrella.entity.Umbrella;
import kr.inhatc.capstone.main.umbrella.repository.UmbrellaRepository;
import kr.inhatc.capstone.members.entity.Members;
import kr.inhatc.capstone.members.repository.MembersRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor // final이 붙은 것을 메모리에 올려줌
public class BoardService {

	private final BoardRepository boardRepository;

	public List<Board> findAllBoard() {
	    
	    List<Board> boardList = boardRepository.findAllBoard();
	    return boardList;
	    
	}
	
	public Board findBoard(Long boardId) {
	    
	    Board board = boardRepository.findBoard(boardId);
	    
	    return board;
	}

	public void boardWrite(Board board) {

        boardRepository.boardWrite( board.getTitle(), board.getContent(), board.getCreateDate(), board.getDeleteYn());
	    
	}
	
	public void boardUpdate(Board board) {

        boardRepository.boardUpdate(board.getId(), board.getTitle(), board.getContent());
        
	}
	
	public void boardDelete(Long boardId) {
	    boardRepository.boardDelete(boardId);
	}
}
