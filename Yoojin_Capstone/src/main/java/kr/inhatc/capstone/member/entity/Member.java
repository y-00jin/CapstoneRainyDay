//package kr.inhatc.capstone.member.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import kr.inhatc.capstone.member.dto.MemberFormDto;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Entity
//@Table(name = "t_member")
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//public class Member {
//
//	@Id
//	@Column(nullable = false, unique = true, length = 20)
//	private String memberId;				// 학번(아이디)
//	
//	@Column(nullable = false, length = 50)	
//	private String password;				// 비밀번호
//	
//	@Column(nullable = false, length = 20)
//	private String name;					//이름
//	
//	@Column(nullable = false, length = 30)
//	private String depart;					//학과
//	
//	@Column(nullable = false, length = 20)
//	private String question;				//질문
//	
//	@Column(nullable = false, length = 50)
//	private String answer;					//답변
//	
//	@Column(length = 10)
//	private String admin;					//관리자 확인
//	
//	public static Member createMrmberAdmin(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
//	
//		Member member = new Member();
//		
//		// 학번
//		member.setMemberId(memberFormDto.getMemberId());
//		
//		// 비밀번호
//		String password = passwordEncoder.encode(memberFormDto.getPassword());
//        member.setPassword(password);
//		
//        // 이름
//        member.setName(memberFormDto.getName());
//        
//        // 학과
//        member.setDepart(memberFormDto.getDepart());
//        
//        // 질문
//        member.setQuestion(memberFormDto.getQuestion());
//        
//        // 답변
//        member.setAnswer(memberFormDto.getAnswer());
//        
//        // 관리자 확인
//        member.setAdmin("Y");
//		return member;
//	}
//}
