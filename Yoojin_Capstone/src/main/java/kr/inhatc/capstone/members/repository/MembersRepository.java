package kr.inhatc.capstone.members.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

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
}
