package kr.inhatc.capstone.main.board.dto;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFormDto {

    
    private Long id;                        // 게시판 번호
    
    @NotBlank(message = "제목은 필수 항목 입니다.")
    private String title;                   // 게시판 제목
    
    @NotBlank(message = "내용은 필수 항목 입니다.")
    private String content;                 // 게시판 내용

    private LocalDateTime createDate;       // 작성일

    private String deleteYn;                // 삭제 여부
    
}
