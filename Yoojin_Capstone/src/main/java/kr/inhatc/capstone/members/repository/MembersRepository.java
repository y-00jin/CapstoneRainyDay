package kr.inhatc.capstone.members.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.capstone.main.rental.entity.Rental;
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
	
	/**
	 * 아이디, 비밀번호로 관리자인지 조회
	 * @param memberDepId
	 * @param password
	 * @return
	 */
	@Query(value = "select m from Members m where m.memberDepId = :memberDepId and m.password = :password")
	Members findByAdmin(@Param ("memberDepId")String memberDepId, @Param ("password")String password);

	/**
	 * 비밀번호 변경
	 * @param password
	 * @param memberDepId
	 */
	@Modifying // update시 추가해줘야함
	@Transactional // update시 추가해줘야함
	@Query(value = "update Members set password =:password where memberDepId = :memberDepId")
	void updateByPassword(@Param ("password")String password, @Param ("memberDepId")String memberDepId);
	
	/**
	 * 입력한 정보로 멤버 조회
	 * @param memberDepId
	 * @param name
	 * @param depart
	 * @return
	 */
	@Query(value = "select * from t_member where member_dep_id like %:memberDepId% or name like %:name% or depart like %:depart%", nativeQuery = true)
	List<Members> MembersFindOr(@Param ("memberDepId")String memberDepId, @Param ("name")String name, @Param ("depart")String depart);
	
}
