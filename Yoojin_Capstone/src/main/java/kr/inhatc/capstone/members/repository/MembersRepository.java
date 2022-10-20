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
//	@Query(value = "select * from t_member m where m.member_dep_id like %:memberDepId% and m.password like %:password%", nativeQuery = true)	//true를 해줘야 *를 인식
//	List<Members> findByMemberDepId(@Param("memberDepId")String memberDepId, @Param("password") String password);
	
	List<Members> findByMemberDepIdAndPassword(String memberDepId, String password);
}
