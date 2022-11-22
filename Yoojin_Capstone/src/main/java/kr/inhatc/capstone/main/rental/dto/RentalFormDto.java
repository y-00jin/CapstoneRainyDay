package kr.inhatc.capstone.main.rental.dto;

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
public class RentalFormDto {

    
    private Long id;            // 대여 코드
    
    private String umName;      // 우산 이름
    
    private String umRentalMemberId;          // 대여 학생
    
    private LocalDateTime rentalDate;       // 대여 날짜
    
    private LocalDateTime returnDate;       // 반납 날짜
       
    private String returnState;             // 반납 상태
    
    private String extensionYn;             // 연장 여부
    
    private String rbRental;        // 라디오 버튼
}
