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
	
}
