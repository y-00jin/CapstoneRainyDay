package kr.inhatc.capstone.main.rental.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.capstone.main.rental.entity.Rental;
import kr.inhatc.capstone.main.rental.repository.RentalRepository;
import kr.inhatc.capstone.main.umbrella.entity.Umbrella;
import kr.inhatc.capstone.main.umbrella.repository.UmbrellaRepository;
import kr.inhatc.capstone.members.entity.Members;
import kr.inhatc.capstone.members.repository.MembersRepository;
import kr.inhatc.capstone.utils.DataUtils;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor // final이 붙은 것을 메모리에 올려줌
public class RentalService {

	private final RentalRepository rentalRepository;
	private final MembersRepository membersRepository;
	private final UmbrellaRepository umbrellaRepository;

	/**
	 * 모든 대여 정보 조회
	 * @return
	 */
	public List<Rental> findAllRental() {
	    
	    List<Rental> rentalList = rentalRepository.findByRental();
	    return rentalList;
	    
	}
	
	/**
	 * 학번과 우산 번호를 이용하여 대여 정보 조회
	 * @param rental
	 * @return
	 */
	public List<Rental> searchRentalOr(Rental rental){
	    
	    List<Rental> rentalList = rentalRepository.findByUmRentalMemberIdOrUmName(rental.getUmRentalMemberId(), rental.getUmName());
        return rentalList;
        
	}
	
	public List<Rental> searchRentalAnd(Rental rental){
        
        List<Rental> rentalList = rentalRepository.findByUmRentalMemberIdAndUmName(rental.getUmRentalMemberId(), rental.getUmName());
        return rentalList;
        
    }

	
	/**
	 * 대여 정보 저장
	 * @param rental
	 * @return
	 */
	public Rental saveRental(Rental rental) {
        return rentalRepository.save(rental); // 저장된 멤버를 리턴
    }

	
	/**
	 * 대여 상태 확인 (대여중인 학번 or 대여 중인 우산)
	 * @param rental
	 * @return
	 */
	public boolean checkRental(Rental rental) {
	    
	    
	    Members checkMembers = membersRepository.findByMemberDepId(rental.getUmRentalMemberId());
	    Umbrella checkUmbrella = umbrellaRepository.umNameCheck(rental.getUmName());
	    
	    if(checkMembers!= null && checkUmbrella != null) {
	        
	        Rental CheckDepReturn = rentalRepository.checkDepReturn(rental.getUmRentalMemberId());     // 대여 중
	        Rental CheckUmReturn =  rentalRepository.checkUmReturn(rental.getUmName());
	    
	        System.out.println("--------------------CheckDepReturn " + CheckDepReturn);
	        System.out.println("------------------------CheckUmReturn " + CheckUmReturn);

	        if(CheckDepReturn == null && CheckUmReturn == null) {      // 둘다 대여 가능
	            return true;
	        }
	        else {
	            return false;
	        }
	    }
	    else {
	        return false; 
	    }
	}
	
	/**
	 * 반납 가능 여부 확인
	 * @param rental
	 * @return
	 */
	public boolean checkReturn(Rental rental) {
	    
	    Members checkMembers = membersRepository.findByMemberDepId(rental.getUmRentalMemberId());
        Umbrella checkUmbrella = umbrellaRepository.umNameCheck(rental.getUmName());
        
        if(checkMembers!= null && checkUmbrella != null) {
            
            Rental CheckReturn = rentalRepository.checkReturn(rental.getUmRentalMemberId(), rental.getUmName());     // 대여 중
        

            if(CheckReturn != null) {      // 반납 가능
                return true;
            }
            else {                         // 반납 할 정보 없음
                return false;
            }
        }
        else {
            return false;
        }
	    
        
        
    }
	
	public void updateRental(Rental rental) {
	    rentalRepository.updateByReturnState(rental.getUmRentalMemberId(), rental.getUmName());
	}

	public List<Rental> myRental(String MemberDepId){
	    List<Rental> rentalList =  rentalRepository.myRental(MemberDepId);
	    return rentalList;
	}
	
	public void updateReturnDate(LocalDateTime returnDateLdt) {
	    
	    
	    String memberDepId = DataUtils.getStaticMemberDep();
	    
	    System.out.println("^^^^^^^^^^^^^^^^^^^^  " + returnDateLdt) ;
	    
	    // ### 1. localDateTime → Calendar로 변환
        Date rentalDate = java.sql.Timestamp.valueOf(returnDateLdt);
        Calendar returnCal = Calendar.getInstance();
        returnCal.setTime(rentalDate);
        returnCal.add(Calendar.DATE, 7);            // 반납 예정일 : 대여날짜 + 7일
        
        // ### 2. Calendar → Date
        Date returnDate = returnCal.getTime();
        
        // ### 3. Date → LocalDateTime
        LocalDateTime returnLdt = returnDate.toInstant()   // Date -> Instant
                .atZone(ZoneId.systemDefault())  // Instant -> ZonedDateTime
                .toLocalDateTime();
        
            
        rentalRepository.updateReturnDate(memberDepId, returnLdt);      // 수정
	    
	    
	}
	
}
