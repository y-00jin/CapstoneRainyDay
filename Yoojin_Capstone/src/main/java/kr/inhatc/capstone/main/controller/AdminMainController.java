package kr.inhatc.capstone.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.inhatc.capstone.main.rental.dto.RentalFormDto;
import kr.inhatc.capstone.main.rental.entity.Rental;
import kr.inhatc.capstone.main.rental.service.RentalService;
import kr.inhatc.capstone.main.umbrella.dto.UmbrellaFormDto;
import kr.inhatc.capstone.main.umbrella.entity.Umbrella;
import kr.inhatc.capstone.main.umbrella.service.UmbrellaService;
import kr.inhatc.capstone.members.dto.MembersFormDto;
import kr.inhatc.capstone.members.entity.Members;
import kr.inhatc.capstone.members.service.MembersService;
import lombok.extern.log4j.Log4j2;

@Controller // 리턴 정보를 클라이언트에 전달
@Log4j2
@RequestMapping("/rainyday/main/admin")
public class AdminMainController {
    
    @Autowired
    UmbrellaService umbrellaService;
    
    @Autowired
    MembersService membersService;
    
    @Autowired
    RentalService rentalService;
    
    private List<Rental> rentalList;
    private List<Members> membersList;
    private List<Umbrella> umbrellaList;
    
    private String umError ="";
    private String setRbUpDel = "등록";
    /**
     * 우산 관리
     * @param model
     * @return
     */
    @GetMapping(value = "/adminUmbrella")
    public String adminUmbrella(Model model) {
        
        UmbrellaFormDto umbrellaFormDto = new UmbrellaFormDto();
        umbrellaFormDto.setUmRentalState("All");
        umbrellaFormDto.setRbUpDel(setRbUpDel);
        
        model.addAttribute("umbrellaFormDto", umbrellaFormDto);
        
        umbrellaList = umbrellaService.findAll();
        model.addAttribute("umbrellaList", umbrellaList);
        model.addAttribute("umError", umError);
       
        return "rainyday/main/admin/adminUmbrella";
    }
    
    /**
     * 우산 관리 대여 상태에 따라 테이블 조회
     * @param model
     * @param umbrellaFormDto
     * @return
     */
    @PostMapping(value = "/adminUmbrella")
    public String adminUmbrella(Model model, UmbrellaFormDto umbrellaFormDto) {
        
        String rentalState = umbrellaFormDto.getUmRentalState();
        umbrellaFormDto.setUmRentalState(rentalState);
        
        if(rentalState.equals("All")) {
            umbrellaList = umbrellaService.findAll();
        }
        else {
            umbrellaList = umbrellaService.findAllUmbrella(umbrellaFormDto.getUmRentalState());
        }
        
        model.addAttribute("umbrellaList", umbrellaList);
       
        return "rainyday/main/admin/adminUmbrella";
    }
    
    /**
     * 우산 관리 등록, 삭제 기능 처리 후 페이지 전환
     * @param model
     * @param umbrellaFormDto
     * @return
     */
    @PostMapping(value = "/adminUmbrellaQuery")
    public String adminUmbrellaQuery(Model model, UmbrellaFormDto umbrellaFormDto) {
        
        System.out.println("!!!!!!!!!!!!!!!!!!!!!1" + umbrellaFormDto.getRbUpDel());
        Umbrella umbrella = Umbrella.createUmbrella(umbrellaFormDto);
        
        // ### 등록 ###
        if(umbrellaFormDto.getRbUpDel().equals("등록")) {
            if(umbrella.getUmName()==null || umbrella.getUmName().equals("")) {
                umError = "등록 및 삭제할 우산번호를 입력해주세요.";
            }else {
                
                boolean umNameCheck = umbrellaService.umNameCheck(umbrella);
                if(umNameCheck == true) {       // 중복된 값
                    umError = "중복된 우산 번호가 있습니다.";
                }
                else {
                    umbrellaService.umInsert(umbrella);     // 추가 작업
                    umError = "";
                }
            }
            setRbUpDel = "등록";
        }
        // ### 삭제 ###
        else {
           
            if(umbrella.getUmName()==null || umbrella.getUmName().equals("")) {
                umError = "등록 및 삭제할 우산번호를 입력해주세요.";
            }else {
                
                boolean umNameCheck = umbrellaService.umNameCheckN(umbrella);
                if(umNameCheck == true) {       // 값 존재
                    
                    
                    umError = "";
                    umbrellaService.umDelete(umbrella);     // 추가 작업
                }
                else {
                    umError = "해당 우산 정보가 없거나 이미 대여 중인 우산입니다.";
                }
            }
            setRbUpDel = "삭제";
        }

        return "redirect:/rainyday/main/admin/adminUmbrella";
    }
    
    
    /**
     * 멤버 관리
     * @param model
     * @return
     */
    @GetMapping(value = "/adminStudent")
    public String adminStudent(Model model) {
        
        model.addAttribute("membersFormDto", new MembersFormDto());
        
        membersList = membersService.MembersFindAll();
        model.addAttribute("membersList", membersList);
        
        
        return "rainyday/main/admin/adminStudent";
    }

    
    /**
     * 멤버 관리 : 학생 조회
     * @param membersFormDto
     * @param model
     * @return
     */
    @PostMapping(value = "/adminStudent")
    public String adminStudent(MembersFormDto membersFormDto, Model model) {

        if(membersFormDto.getMemberSelect().equals("")) {
            membersList = membersService.MembersFindAll();
        }
        else {
            membersList = membersService.MembersFindOr(membersFormDto.getMemberSelect());
        }
        
        model.addAttribute("membersList", membersList);
        return "rainyday/main/admin/adminStudent";
    }
    
    
    /**
     * 대여 관리
     * @param model
     * @return
     */
    @GetMapping(value = "/adminRental")
    public String adminRental(Model model) {
        
        RentalFormDto rentalFormDto = new RentalFormDto();
        rentalFormDto.setRbRental("rbSearch");
        
        model.addAttribute("rentalFormDto", rentalFormDto);
        
        List<Rental> rentalList = rentalService.findAllRental();
        model.addAttribute("rentalList", rentalList);
        model.addAttribute("rentError", "");
        
        return "rainyday/main/admin/adminRental";
    }
    
    /**
     * 대여 관리 : 검색, 대여, 반납 기능
     * @param rentalFormDto
     * @param model
     * @return
     */
    @PostMapping(value = "/adminRental")
    public String adminRental(RentalFormDto rentalFormDto, Model model) {

        Rental rental = Rental.createRental(rentalFormDto);
        
        
        String rbSelect = rentalFormDto.getRbRental();
        
        // ### 검색
        if(rbSelect.equals("rbSearch")) {  
            
            if((rentalFormDto.getUmRentalMemberId() == null || rentalFormDto.getUmRentalMemberId().equals("")) && (rentalFormDto.getUmName() == null || rentalFormDto.getUmName().equals(""))) {
                rentalList = rentalService.findAllRental();
            }else {
                rentalList = rentalService.searchRentalOr(rental);
            }
            rentalFormDto.setRbRental("rbSearch");
        }
        // ### 대여
        else if(rbSelect.equals("rbRent")) {
            
            // 1. 아무것도 입력하지 않은 경우
            if((rentalFormDto.getUmRentalMemberId() == null || rentalFormDto.getUmRentalMemberId().equals("")) || (rentalFormDto.getUmName() == null || rentalFormDto.getUmName().equals(""))) {
                model.addAttribute("rentError", "대여 정보를 입력해주세요.");
                rentalList = rentalService.findAllRental();
            }
            // 2. 정보 입력
            else {
                
               
                
                
                boolean check = rentalService.checkRental(rental);      // 중복 확인
                
                // # 대여 가능
                if(check == true) {
                    
                    rentalService.saveRental(rental);                   // 정보 저장
                    
                    Umbrella umbrella = Umbrella.createUmbrella(rentalFormDto.getUmName(), "Y");
                    umbrellaService.updateUmRentalState(umbrella);           // 우산 대여 상태 변경
                    
                    rentalList = rentalService.searchRentalAnd(rental); // 테이블 재구성
                    model.addAttribute("rentError", "");
                } 
                // # 대여 불가능
                else {
                    model.addAttribute("rentError", "대여 정보를 확인하세요.");
                    rentalList = rentalService.findAllRental();
                }
            }
            
            rentalFormDto.setRbRental("rbRent");
            
        }  
        // ### 반납
        else if(rbSelect.equals("rbReturn")) {
            
            // 1. 아무것도 입력하지 않은 경우
            if((rentalFormDto.getUmRentalMemberId() == null || rentalFormDto.getUmRentalMemberId().equals("")) || (rentalFormDto.getUmName() == null || rentalFormDto.getUmName().equals(""))) {
                model.addAttribute("rentError", "반납 정보를 입력해주세요.");
                rentalList = rentalService.findAllRental();
            }
            // 2. 정보 입력
            else {
                boolean check = rentalService.checkReturn(rental);
                
                // # 반납 가능
                if(check == true) {   
                    
                    rentalService.updateRental(rental);     // 대여 정보 변경
                    
                    Umbrella umbrella = Umbrella.createUmbrella(rentalFormDto.getUmName(), "N");
                    umbrellaService.updateUmRentalState(umbrella);           // 우산 대여 상태 변경
                    
                    model.addAttribute("rentError", "");
                    
                    
                } 
                // # 반납할 정보 없음
                else {
                    model.addAttribute("rentError", "반납 정보를 확인하세요.");
                   
                }
                rentalList = rentalService.findAllRental();
            }
            rentalList = rentalService.findAllRental();
            rentalFormDto.setRbRental("rbReturn");
        }
        
        model.addAttribute("rentalList", rentalList);
        model.addAttribute("rentalFormDto", rentalFormDto);

        return "rainyday/main/admin/adminRental";
    }
    
    @GetMapping(value = "/adminNotice")
    public String adminNotice(Model model) {
//        model.addAttribute("membersFormDto", new MembersFormDto());
//        model.addAttribute("loginCheck", "");
        System.out.println("qqqqqqq");
        return "rainyday/main/admin/adminNotice";
    }
}
