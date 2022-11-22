package kr.inhatc.capstone.main.umbrella.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import kr.inhatc.capstone.main.umbrella.entity.Umbrella;

public interface UmbrellaRepository extends JpaRepository<Umbrella, Long>, QuerydslPredicateExecutor<Umbrella>{

    /**
     * 우산 반납 상태로 정보 조회
     * @param umRentalState
     * @return
     */
    @Query(value = "select * from t_umbrella where um_rental_state =:umRentalState", nativeQuery = true)
    List<Umbrella> findRentalState(@Param ("umRentalState")String umRentalState);
    
    @Query(value = "select count(*) from t_umbrella", nativeQuery = true)
    int countUmAll();
    
    @Query(value = "select count(*) from t_umbrella where um_rental_state ='Y'", nativeQuery = true)
    int countUmRentallY();
}
