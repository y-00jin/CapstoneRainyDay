package kr.inhatc.capstone.main.board.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.inhatc.capstone.main.board.dto.BoardFormDto;
import kr.inhatc.capstone.main.rental.dto.RentalFormDto;
import kr.inhatc.capstone.main.rental.entity.Rental;
import kr.inhatc.capstone.main.umbrella.dto.UmbrellaFormDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_board")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql : IDENTITY / oracle : SEQUENCE
    @Column(name = "board_id", unique = true)
    private Long id;                        // 게시판 번호
    
    
    @Column(nullable = false, length = 100)     // 제목
    private String title;
    
    @Column(nullable = false, length = 5000)   // 내용
    private String content;

    private LocalDateTime createDate;       // 작성일

    @Column(length = 10)
    private String deleteYn;                // 삭제 여부
    
    public static Board createBoardNoId(BoardFormDto boardFormDto) {
        Board board = new Board();
        
        
        board.setTitle(boardFormDto.getTitle());        // 제목
        
        board.setContent(boardFormDto.getContent());    // 제목
        
        board.setDeleteYn("N");                         // 삭제 여부

        // 작성일
        LocalDateTime boardLdt = LocalDateTime.now();          // 현재 날짜 가져오기
        board.setCreateDate(boardLdt);
       

        return board;
    }
    
    public static Board createBoard(BoardFormDto boardFormDto) {
        Board board = new Board();
        
        board.setId(boardFormDto.getId());
        
        board.setTitle(boardFormDto.getTitle());        // 제목
        
        board.setContent(boardFormDto.getContent());    // 제목
        
        board.setDeleteYn("N");                         // 삭제 여부

        // 작성일
        LocalDateTime boardLdt = LocalDateTime.now();          // 현재 날짜 가져오기
        board.setCreateDate(boardLdt);
       

        return board;
    }
}
