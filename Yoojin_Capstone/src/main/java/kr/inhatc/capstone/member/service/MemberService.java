//package kr.inhatc.capstone.member.service;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import kr.inhatc.capstone.member.entity.Member;
//import kr.inhatc.capstone.member.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@Transactional
//@RequiredArgsConstructor    //final이 붙은 것을 메모리에 올려줌
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//    
//    public Member saveMember(Member member) {
//        
//        validateDuplicate(member);  // 사용자 중복 체크
//        
//        return memberRepository.save(member);   // 저장된 멤버를 리턴
//    }
//
//    /**
//     * 사용자 중복 체크
//     * @param member
//     */
//    private void validateDuplicate(Member member) {
//        Member findMember = memberRepository.findByMemberId(member.getMemberId());
//        if(findMember != null) {    // 중복
//            throw new IllegalStateException(">>>>>이미 등록된 사용자 입니다.");
//            
//        }
//    }
//}
