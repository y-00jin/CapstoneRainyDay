package kr.inhatc.capstone.members.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MembersFormDto {

	private String memberDepId;				// 학번(아이디)
	
	private String password;				// 비밀번호
	
	private String name;					//이름
	
	private String depart;					//학과
	
	private String question;				//질문
	
	private String answer;					//답변
	
	private String admin;					//관리자 확인
	
}
