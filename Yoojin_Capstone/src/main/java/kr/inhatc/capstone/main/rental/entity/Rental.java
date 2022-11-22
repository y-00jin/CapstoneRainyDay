package kr.inhatc.capstone.main.rental.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private Long id;            // 상품 코드
    
    
    @Column(length = 20)
    private String umName;
    
    @Column(length = 20)
    private String memberDepId;             // 대여 상태
    
    @Column(length = 20) 
    private String umRentalMemberId;          // 대여 학생
    
    
}
