package kr.inhatc.capstone.main.umbrella.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.inhatc.capstone.main.umbrella.entity.Umbrella;
import kr.inhatc.capstone.main.umbrella.repository.UmbrellaRepository;
import kr.inhatc.capstone.members.entity.Members;
import kr.inhatc.capstone.members.repository.MembersRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor // final이 붙은 것을 메모리에 올려줌
public class UmbrellaService {

	private final UmbrellaRepository umbrellaRepository;

	public List<Umbrella> findAllUmbrella(String rentalState) {
	    
	    List<Umbrella> umbrellaList = umbrellaRepository.findRentalState(rentalState);
	    return umbrellaList;
	    
	}
	
	public List<Umbrella> findAll() {
        
        List<Umbrella> umbrellaList = umbrellaRepository.findAll();
        return umbrellaList;
        
    }

	public int countUmAll() {
	    return umbrellaRepository.countUmAll();
	}

	public int countUmRentallY() {
        return umbrellaRepository.countUmRentallY();
    }
	
	/**
	 * 우산 정보 조회
	 * @param umbrella
	 * @return
	 */
	public boolean umNameCheck(Umbrella umbrella) {
	    
	    if(umbrellaRepository.umNameCheck(umbrella.getUmName()) != null) {
	        return true;       // 중복된 값
	    }
	    else {
	        return false;      // 가능한 값
	    }
	}
	
	/**
     * 우산 정보 조회 (대여상태가 N인것)
     * @param umbrella
     * @return
     */
    public boolean umNameCheckN(Umbrella umbrella) {
        
        if(umbrellaRepository.umNameCheckN(umbrella.getUmName()) != null) {
            return true;       // 중복된 값
        }
        else {
            return false;      // 가능한 값
        }
    }
	
	
	/**
	 * 우산 정보 추가
	 * @param umbrella
	 */
	public void umInsert(Umbrella umbrella) {
	    umbrellaRepository.umInsert(umbrella.getUmName());
	}
	
	/**
	 * 우산 정보 삭제
	 * @param umbrella
	 */
	public void umDelete(Umbrella umbrella) {
        umbrellaRepository.umDelete(umbrella.getUmName());
    }
	
	/**
	 * 우산 대여 정보 변경
	 * @param umbrella
	 */
	public void updateUmRentalState(Umbrella umbrella) {
	    System.out.println("야호");
	    umbrellaRepository.updateUmRentalState(umbrella.getUmName(), umbrella.getUmRentalState());
	}
	
}
