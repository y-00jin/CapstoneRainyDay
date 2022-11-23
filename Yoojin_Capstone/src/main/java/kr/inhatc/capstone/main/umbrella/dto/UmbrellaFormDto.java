package kr.inhatc.capstone.main.umbrella.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UmbrellaFormDto {

    private String umName;                       // 우산 이름
    
    private String umRentalState;             // 대여 상태
    
    private String rbUpDel;             // 등록, 삭제 라디오 버튼
    
    
}
