package kr.inhatc.capstone.members.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.capstone.members.entity.Members;

public interface MembersRepository extends JpaRepository<Members, Long>, QuerydslPredicateExecutor<Members>{

	/**
	 * 로그인 시 아이디와 비밀번호로 조회
	 * @param memberDepId
	 * @param password
	 * @return
	 */
	List<Members> findByMemberDepIdAndPassword(String memberDepId, String password);
	
	/**
	 * 회원가입 시 아이디 중복 체크
	 * @param memberDepId
	 * @return
	 */
	Members findByMemberDepId(String memberDepId);
	
	/**
	 * 아이디 찾기 시 입력 정보로 아이디 조회
	 * @param name
	 * @param depart
	 * @param question
	 * @param answer
	 * @return
	 */
	Members findByNameAndDepartAndQuestionAndAnswer(String name, String depart, String question, String answer);
	
	/**
	 * 비밀번호 찾기 시 입력 정보로 아이디 조회
	 * @param memberDepId
	 * @param name
	 * @param depart
	 * @param question
	 * @param answer
	 * @return
	 */
	Members findByMemberDepIdAndNameAndDepartAndQuestionAndAnswer(String memberDepId,String name, String depart, String question, String answer);
	
	@Modifying // update시 추가해줘야함
	@Transactional // update시 추가해줘야함
	@Query(value = "update Members set password =:password where member_dep_id = :memberDepId")
	void updateByPassword(@Param ("password")String password, @Param ("memberDepId")String memberDepId);
	

}
