package kr.inhatc.capstone.main.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.capstone.main.rental.entity.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long>, QuerydslPredicateExecutor<Rental> {

    
    /**
     * 대여 정보 조회
     * @return
     */
    @Query(value = "SELECT rental_id, um_name, um_rental_member_id, rental_date, return_date, return_state, " +
            "extension_yn FROM t_rental WHERE return_state ='N'" , nativeQuery = true)
    List<Rental> findByRental();
    
       
    List<Rental> findByUmRentalMemberIdOrUmName(String umRentalMemberId, String umName);
    
    List<Rental> findByUmRentalMemberIdAndUmName(String umRentalMemberId, String umName);
    
    /**
     * 학번 반납 상태 확인 (중복 대여 방지)
     * @return
     */
    @Query(value = "select * from t_rental where return_state ='N' and um_rental_member_id = :umRentalMemberId", nativeQuery = true)
    Rental checkDepReturn(@Param ("umRentalMemberId")String umRentalMemberId);
    

    /**
     * 우산 대여 상태 확인
     * @param umRentalMemberId
     * @return
     */
    @Query(value = "select * from t_rental where return_state ='N' and um_name = :umName", nativeQuery = true)
    Rental checkUmReturn(@Param ("umName")String umName);
    
    /**
     * 반납 상태 확인
     * @param umRentalMemberId
     * @param umName
     * @return
     */
    @Query(value = "select * from t_rental where return_state ='N' and um_name = :umName and um_rental_member_id =:umRentalMemberId", nativeQuery = true)
    Rental checkReturn(@Param ("umRentalMemberId")String umRentalMemberId, @Param ("umName")String umName);
    
    
    @Modifying // update시 추가해줘야함
    @Transactional // update시 추가해줘야함
    @Query(value = "update t_rental set return_state ='Y' where return_state ='N' and um_name = :umName and um_rental_member_id =:umRentalMemberId", nativeQuery = true)
    void updateByReturnState(@Param ("umRentalMemberId")String umRentalMemberId, @Param ("umName")String umName);
    
    @Query(value = "SELECT * FROM t_rental WHERE return_state ='N' and um_rental_member_id = :umRentalMemberId" , nativeQuery = true)
    List<Rental> myRental(@Param ("umRentalMemberId")String umRentalMemberId);
    
    
}
