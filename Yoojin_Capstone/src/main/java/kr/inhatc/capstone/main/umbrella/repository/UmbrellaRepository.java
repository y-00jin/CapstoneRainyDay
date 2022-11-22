package kr.inhatc.capstone.main.umbrella.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kr.inhatc.capstone.main.umbrella.entity.Umbrella;

public interface UmbrellaRepository extends JpaRepository<Umbrella, Long>, QuerydslPredicateExecutor<Umbrella>{

    /**
     * 모든 우산 정보 리턴
     * @return
     */
//    @Query(value = "select * from t_umbrella u "
//            + "order by u.um_id desc", nativeQuery = true)  //true를 해줘야 *를 인식
//    List<Umbrella> findAllUmbrella();
    
    

}
