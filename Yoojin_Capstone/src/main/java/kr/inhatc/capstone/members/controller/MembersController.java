package kr.inhatc.capstone.members.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.inhatc.capstone.members.dto.MembersFormDto;
import kr.inhatc.capstone.members.entity.Members;
import kr.inhatc.capstone.members.service.MembersService;
import lombok.extern.log4j.Log4j2;

@Controller // 리턴 정보를 클라이언트에 전달
@Log4j2
@RequestMapping("/rainyday/members")
public class MembersController {

    @Autowired
    MembersService membersService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private String staticMemberDep;
    
    /**
     * 시작 화면 introPage
     * 
     * @param model
     * @return
     */
    @GetMapping(value = "/introPage")
    public String introPage(Model model) {
        model.addAttribute("membersFormDto", new MembersFormDto());
        model.addAttribute("loginCheck", "");
        return "rainyday/members/introPage";
    }

    /**
     * introPage에서 submit 클릭 -> DB에서 아이디, 비밀번호 조회
     * 
     * @param membersFormDto
     * @return
     */
    @PostMapping(value = "/introPage")
    public String introPage(MembersFormDto membersFormDto, Model model) {

        Members members = Members.createMembers(membersFormDto); // 웹에서 submit해서 받은 정보로 member 객체 만들기

        System.out.println("=========================> 웹에서 받은 멤버로 만든 객체 " + members);

        boolean loginCheck = membersService.loginMember(members);

        System.out.println("=========================> 로그인 체크 " + loginCheck);

        if (loginCheck == true) { // 로그인 성공
            return "redirect:/"; // 메인 페이지로 이동
        } else {
            model.addAttribute("loginCheck", "아이디 및 비밀번호를 확인하세요");
            return "rainyday/members/introPage"; // 로그인 실패
        }
    }

    /**
     * 사용자 회원가입
     * 
     * @param model
     * @return
     */
    @GetMapping(value = "/signUpUser")
    public String signUpUser(Model model) {
        model.addAttribute("membersFormDto", new MembersFormDto());
        model.addAttribute("userCheck", "");
        return "rainyday/members/signUpUser";
    }

    /**
     * 사용자 회원가입 : signUpUser에서 SignUp 클릭
     * 
     * @param model
     * @return
     */
    @PostMapping(value = "/signUpUser")
    public String signUpUser(MembersFormDto membersFormDto, Model model) {

        log.info("===============================> " + membersFormDto);
        Members members = Members.createMembers(membersFormDto); // 웹에서 submit해서 받은 정보로 member 객체 만들기

        boolean memberCheck = membersService.validateDuplicate(members);

        if (memberCheck == false || members.getMemberDepId().equals("") || members.getMemberDepId() == null) { // 아이디
                                                                                                               // 중복일 경우
            model.addAttribute("userCheck", "이미 사용중인 아이디입니다.");
            return "rainyday/members/signUpUser";
        } else {
            model.addAttribute("userCheck", "사용 가능한 아이디입니다.");

            // 모든 정보를 입력하지 않았을 경우
            if (members.getMemberDepId() == null || members.getMemberDepId().equals("") || members.getPassword() == null
                    || members.getPassword().equals("") || members.getDepart() == null || members.getDepart().equals("")
                    || members.getAnswer() == null || members.getAnswer().equals("") || members.getQuestion() == null
                    || members.getQuestion().equals("") || members.getName() == null || members.getName().equals("")) {
                model.addAttribute("spaceCheck", "정보를 모두 입력해주세요.");
                return "rainyday/members/signUpUser";

            } else {
                System.out.println("********************************** dddd");
                membersService.saveMember(members);
                return "rainyday/members/introPage";
            }

        }

    }
    
    /**
     * 관리자회원가입
     * @param model
     * @return
     */
    @GetMapping(value = "/signUpAdmin")
    public String signUpAdmin(Model model) {
        model.addAttribute("membersFormDto", new MembersFormDto());
        model.addAttribute("userCheck", "");
        return "rainyday/members/signUpAdmin";
    }
    
    /**
     * 관리자 회원가입 : signUpAdminr에서 SignUp 클릭
     * 
     * @param model
     * @return
     */
    @PostMapping(value = "/signUpAdmin")
    public String signUpAdmin(MembersFormDto membersFormDto, Model model) {

        log.info("===============================> " + membersFormDto);
        Members members = Members.createMembers(membersFormDto); // 웹에서 submit해서 받은 정보로 member 객체 만들기

        boolean memberCheck = membersService.validateDuplicate(members);    // 중복 체크

        if (memberCheck == false || members.getMemberDepId().equals("") || members.getMemberDepId() == null) { // 아이디
                                                                                                               // 중복일 경우
            model.addAttribute("userCheck", "이미 사용중인 아이디입니다.");
            return "rainyday/members/signUpAdmin";
        } else {
            model.addAttribute("userCheck", "사용 가능한 아이디입니다.");

            // 모든 정보를 입력하지 않았을 경우
            if (members.getMemberDepId() == null || members.getMemberDepId().equals("") || members.getPassword() == null
                    || members.getPassword().equals("") || members.getDepart() == null || members.getDepart().equals("")
                    || members.getAnswer() == null || members.getAnswer().equals("") || members.getQuestion() == null
                    || members.getQuestion().equals("") || members.getName() == null || members.getName().equals("") || members.getAdmin() == null || members.getAdmin().equals("")) {
                model.addAttribute("spaceCheck", "정보를 모두 입력해주세요.");
                return "rainyday/members/signUpAdmin";

            } else {
                
                if(members.getAdmin() != null && members.getAdmin().equals("Y")) {
                    System.out.println("********************************** dddd");
                    membersService.saveMember(members);
                    return "rainyday/members/introPage";
                }else {
                    model.addAttribute("spaceCheck", "관리자용 키를 확인해주세요.");
                    return "rainyday/members/signUpAdmin";
                }
                
                
            }

        }

    }
    
    /**
     * 아이디 찾기
     * @param model
     * @return
     */
    @GetMapping(value = { "/findId", "/findIdDetail"})
    public String findId(Model model) {
        model.addAttribute("membersFormDto", new MembersFormDto());
        return "rainyday/members/findId";
    }
    
    
    /**
     * findId에서 정보 입력 후 submit
     * 
     * @param membersFormDto
     * @return
     */
    @PostMapping(value = { "/findId", "/findIdDetail"})
    public String findIdDetail(MembersFormDto membersFormDto, Model model) {

        Members members = Members.createMembers(membersFormDto); // 웹에서 submit해서 받은 정보로 member 객체 만들기

        System.out.println("=========================> 웹에서 받은 멤버로 만든 객체 " + members);

        if(members.getDepart() == null || members.getDepart().equals("") || members.getAnswer() == null || members.getAnswer().equals("") 
           || members.getQuestion() == null || members.getQuestion().equals("") || members.getName() == null || members.getName().equals("")) 
        {
            model.addAttribute("contentCheck", "정보를 모두 입력해주세요.");
            return "rainyday/members/findId"; 
        }else{
            Members checkMembers = membersService.findId(members);
            if(checkMembers == null || checkMembers.getMemberDepId() == null || checkMembers.getMemberDepId().equals("")) {
                model.addAttribute("contentCheck", "입력한 정보와 일치하는 아이디가 없습니다.");
                return "rainyday/members/findId"; 
            }else {
                model.addAttribute("checkMembers", checkMembers.getMemberDepId());
                return "rainyday/members/findIdDetail";
            }
           
        }

    }
    
    /**
     * 비밀번호 찾기
     * @param model
     * @return
     */
    @GetMapping(value = "/findPw")
    public String findPw(Model model) {
        model.addAttribute("membersFormDto", new MembersFormDto());
        return "rainyday/members/findPw";
    }
    

    /**
     * 비밀번호 찾기에서 정보 입력 후 submit
     * @param membersFormDto
     * @param model
     * @return
     */
    @PostMapping(value = "/findPw")
    public String findPw(MembersFormDto membersFormDto, Model model) {

        Members members = Members.createMembers(membersFormDto); // 웹에서 submit해서 받은 정보로 member 객체 만들기


        System.out.println("*********************************" + members);
        
        if(members.getMemberDepId() == null || members.getMemberDepId().equals("") ||members.getDepart() == null || members.getDepart().equals("") || members.getAnswer() == null || members.getAnswer().equals("") 
           || members.getQuestion() == null || members.getQuestion().equals("") || members.getName() == null || members.getName().equals("")) 
        {
            model.addAttribute("contentCheck", "정보를 모두 입력해주세요.");
            return "rainyday/members/findPw"; 
        }else{
            Members checkMembers = membersService.findId(members);
            if(checkMembers == null || checkMembers.getMemberDepId() == null || checkMembers.getMemberDepId().equals("")) {
                model.addAttribute("contentCheck", "입력한 정보와 일치하는 아이디가 없습니다.");
                return "rainyday/members/findPw"; 
            }else {
                staticMemberDep = checkMembers.getMemberDepId();
                return "rainyday/members/findPwDetail";
            }
           
        }

    }
    
    
    /**
     * 비밀번호 변경
     * @param model
     * @return
     */
    @GetMapping(value = "/findPwDetail")
    public String findPwDetail(Model model) {
        model.addAttribute("membersFormDto", new MembersFormDto());
        return "rainyday/members/findPwDetail";
    }
    
    /**
     * 비밀번호 변경에서 새 비밀번호 입력 후 submit클릭
     * @param membersFormDto
     * @param model
     * @return
     */
    @PostMapping(value = "/findPwDetail")
    public String findPwDetail(MembersFormDto membersFormDto, Model model) {
        
        Members members = Members.createMembers(membersFormDto); // 웹에서 submit해서 받은 정보로 member 객체 만들기
        members.setMemberDepId(staticMemberDep);
        System.out.println("+++++++++++++++++++++++++++++++++++ " + members);
        
        if(members.getPassword() == null || members.getPassword().equals("")) {
            model.addAttribute("contentCheck", "정보를 모두 입력해주세요.");
            return "rainyday/members/findPwDetail";
        }else {
            membersService.updatePw(members);
            return "rainyday/members/introPage";
        }
        
    }

}
