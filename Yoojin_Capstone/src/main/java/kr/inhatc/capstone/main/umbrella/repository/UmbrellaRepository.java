package kr.inhatc.capstone.main.umbrella.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
    
    @Modifying
    @Query(value = "insert into t_umbrella (um_name, um_rental_state) values(:umName, 'N')", nativeQuery = true)
    void umInsert(@Param ("umName")String umName);
    
    @Query(value = "select * from t_umbrella where um_name =:umName", nativeQuery = true)
    Umbrella umNameCheck(@Param ("umName")String umName);
    
    @Query(value = "select * from t_umbrella where um_name =:umName and um_rental_state = 'N'", nativeQuery = true)
    Umbrella umNameCheckN(@Param ("umName")String umName);
    
    @Modifying
    @Transactional
    @Query(value = "delete from t_umbrella where um_name =:umName", nativeQuery = true)
    void umDelete(@Param ("umName")String umName);
    
    
    @Modifying // update시 추가해줘야함
    @Transactional // update시 추가해줘야함
    @Query(value = "update t_umbrella set um_rental_state = :umRentalState where um_name =:umName", nativeQuery = true)
    void updateUmRentalState(@Param ("umName")String umName, @Param ("umRentalState")String umRentalState);
    
}
