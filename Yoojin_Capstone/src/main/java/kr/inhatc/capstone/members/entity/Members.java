package kr.inhatc.capstone.members.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import kr.inhatc.capstone.members.constant.Role;
import kr.inhatc.capstone.members.dto.MembersFormDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_member")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Members {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Mysql의 autoincrement
    @Column(name = "member_id")
    private Long id;
	
	@Column(nullable = false, unique = true, length = 20)
	private String memberDepId;				// 학번(아이디)
	
	@Column(nullable = false, length = 100)	
	private String password;				// 비밀번호
	
	@Column(nullable = false, length = 20)
	private String name;					//이름
	
	@Column(nullable = false, length = 100)
	private String depart;					//학과
	
	@Column(nullable = false, length = 100)
	private String question;				//질문
	
	@Column(nullable = false, length = 100)
	private String answer;					//답변
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
//	@Column(length = 10)
//	private String admin;					//관리자 확인

	public static Members createMembers(MembersFormDto membersFormDto, PasswordEncoder passwordEncoder) {
		Members members = new Members();
		
		members.setMemberDepId(membersFormDto.getMemberDepId());	// 학번

//		if(membersFormDto.getPassword() != null) {
//		    String password = passwordEncoder.encode(membersFormDto.getPassword());   // 비밀번호
//		    members.setPassword(password);
//		}
		members.setPassword(membersFormDto.getPassword());
		
		members.setName(membersFormDto.getName());					// 이름
		members.setDepart(membersFormDto.getDepart());				// 학과
		members.setQuestion(membersFormDto.getQuestion());			// 질문
		members.setAnswer(membersFormDto.getAnswer());				// 답변
		
		if(membersFormDto.getAdmin() == null || membersFormDto.getAdmin().equals("")) {	// 사용자
			members.setRole(Role.USER);
		} else if(membersFormDto.getAdmin() != null && membersFormDto.getAdmin().equals("rainydayAdminKey")) {								// 관리자
		    members.setRole(Role.ADMIN);
		}
		
		return members;
	}
	
}
