package kr.inhatc.capstone.members.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MembersFormDto {

    @NotBlank(message = "학번은 필수 항목 입니다.")
	private String memberDepId;				// 학번(아이디)
	
    @NotBlank(message = "비밀번호는 필수 항목 입니다.")
	private String password;				// 비밀번호
	
    @NotBlank(message = "이름은 필수 항목 입니다.")
	private String name;					//이름
	
    @NotBlank(message = "학과는 필수 항목 입니다.")
	private String depart;					//학과
	
    @NotBlank(message = "질문은 필수 항목 입니다.")
	private String question;				//질문
	
    @NotBlank(message = "답변은 필수 항목 입니다.")
	private String answer;					//답변
	
	private String admin;					//관리자 확인
	
}
