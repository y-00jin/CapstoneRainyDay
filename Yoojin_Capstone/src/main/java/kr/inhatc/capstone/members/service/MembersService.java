package kr.inhatc.capstone.members.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.capstone.main.umbrella.entity.Umbrella;
import kr.inhatc.capstone.members.entity.Members;
import kr.inhatc.capstone.members.repository.MembersRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor // final이 붙은 것을 메모리에 올려줌
public class MembersService {

	private final MembersRepository membersRepository;

	
	public Members saveMember(Members members) {
	    validateDuplicate(members); // 사용자 중복 체크
		return membersRepository.save(members); // 저장된 멤버를 리턴
	}
	
	 /**
     * 사용자 중복 체크
     * @param member
     */
    public void validateDuplicate(Members members) {
        Members findMemberDepId = membersRepository.findByMemberDepId(members.getMemberDepId());
        if (findMemberDepId != null) { // 중복
            throw new IllegalStateException("이미 등록된 사용자 입니다.");
        }
    }
    
    /**
    * 사용자 중복 체크
    * @param member
    */
   public boolean validateDuplicateMember(Members members) {
       Members findMemberDepId = membersRepository.findByMemberDepId(members.getMemberDepId());
       if(findMemberDepId != null) {    // 중복
           return false;
       }
       else {
           return true;
       }
   }
	
	/**
	 * 로그인 시 아이디와 비밀번호로 조회
	 * @param memberDepId
	 * @param password
	 * @return
	 */
	public boolean loginMember(Members members) {
		
		
		System.out.println("=========================> 서비스 members " + members);
		
		if (members.getMemberDepId().equals("") && members.getPassword().equals("")) {
			return false;
		}
		
		List<Members> membersList = membersRepository.findByMemberDepIdAndPassword(members.getMemberDepId(), members.getPassword());	// 웹에서 받은 아이디, 비밀번호로 조화
		
		System.out.println("=========================> 서비스 membersList " + membersList);

		if(membersList.isEmpty() == true) {	// isEmpty() == true : 요소를 가지고 있지 않을 때 => 로그인 정보 조회 실패
			return false;
		}
		
		else {						// isEmpty() == false : 요소를 가지고 있을 때 => 로그인 정보 조회 성공
			return true;
		}
		
	}
	
	public Members findId(Members members) {
	    System.out.println("=========================> members " + members);
	    Members checkMembers = membersRepository.findByNameAndDepartAndQuestionAndAnswer(members.getName(), members.getDepart(), members.getQuestion(), members.getAnswer());
	    return checkMembers;
	}
	
	public Members findPw(Members members) {
        System.out.println("=========================> members " + members);
        Members checkMembers = membersRepository.findByMemberDepIdAndNameAndDepartAndQuestionAndAnswer(members.getMemberDepId() ,members.getName(), members.getDepart(), members.getQuestion(), members.getAnswer());
        return checkMembers;
    }
	
	public void updatePw(Members members) {
	     membersRepository.updateByPassword(members.getPassword(), members.getMemberDepId());
	}
	
	public Members findByAdmin(Members members) {
	    Members findMembers =  membersRepository.findByAdmin(members.getMemberDepId(), members.getPassword());
	    if(findMembers != null) {
	         return findMembers;
	    }
	    else {
	        return new Members();
	    }
	   
	    
	}
	
	public List<Members> MembersFindAll() {
        
        List<Members> membersList = membersRepository.findAll();
        return membersList;
	    
        
        
    }
	
}
