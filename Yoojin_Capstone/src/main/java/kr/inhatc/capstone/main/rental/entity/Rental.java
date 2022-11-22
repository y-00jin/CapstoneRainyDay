package kr.inhatc.capstone.main.rental.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import kr.inhatc.capstone.main.rental.dto.RentalFormDto;
import kr.inhatc.capstone.members.constant.Role;
import kr.inhatc.capstone.members.dto.MembersFormDto;
import kr.inhatc.capstone.members.entity.Members;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_rental")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql : IDENTITY / oracle : SEQUENCE
    @Column(name = "rental_id",  unique = true)
    private Long id;            // 대여 코드
    
    @Column(length = 20)
    private String umName;      // 우산 이름
        
    @Column(length = 20) 
    private String umRentalMemberId;          // 대여 학생
    
    private LocalDateTime rentalDate;       // 대여 날짜
    
    private LocalDateTime returnDate;       // 반납 날짜
       
    @Column(length = 5) 
    private String returnState;             // 반납 상태
    
    @Column(length = 5) 
    private String extensionYn;             // 연장 여부
    
    
    public static Rental createRental(RentalFormDto rentalFormDto) {
        Rental rental = new Rental();
        
        // 대여 학번
        rental.setUmRentalMemberId(rentalFormDto.getUmRentalMemberId());
        
        // 대여 우산
        rental.setUmName(rentalFormDto.getUmName());
        
        // 대여 날짜
        LocalDateTime rentalLdt = LocalDateTime.now();          // 현재 날짜 가져오기
        rental.setRentalDate(rentalLdt);

        
        // 반납 예정일
        
        // ### 1. localDateTime → Calendar로 변환
        Date rentalDate = java.sql.Timestamp.valueOf(rentalLdt);
        Calendar returnCal = Calendar.getInstance();
        returnCal.setTime(rentalDate);
        returnCal.add(Calendar.DATE, 7);            // 반납 예정일 : 대여날짜 + 7일
        
        // ### 2. Calendar → Date
        Date returnDate = returnCal.getTime();
        
        // ### 3. Date → LocalDateTime
        LocalDateTime returnLdt = returnDate.toInstant()   // Date -> Instant
                .atZone(ZoneId.systemDefault())  // Instant -> ZonedDateTime
                .toLocalDateTime();
        rental.setReturnDate(returnLdt);
        
        //반납 상태
        rental.setReturnState("N");
        
        // 연장 유무
        rental.setExtensionYn("N");

        return rental;
    }
    
}
