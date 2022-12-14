package kr.inhatc.capstone.main.umbrella.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.inhatc.capstone.main.rental.entity.Rental;
import kr.inhatc.capstone.main.umbrella.dto.UmbrellaFormDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_umbrella")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Umbrella {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // mysql : IDENTITY / oracle : SEQUENCE
    @Column(name = "um_id")
    private Long id;            // 상품 코드
    
    
    @Column(length = 20, unique = true)
    private String umName;
    
    @Column(length = 20)
    private String umRentalState;             // 대여 상태

    public static Umbrella createUmbrella(UmbrellaFormDto umbrellaFormDto) {
        Umbrella umbrella = new Umbrella();
        
        umbrella.setUmName(umbrellaFormDto.getUmName()); // 우산명
        umbrella.setUmRentalState("N");                  // 대여 상태
        
        return umbrella;
    }
    
    public static Umbrella createUmbrella(String umName, String umRentalState) {
        Umbrella umbrella = new Umbrella();
        
        umbrella.setUmName(umName); // 우산명
        umbrella.setUmRentalState(umRentalState);                  // 대여 상태
        
        return umbrella;
    }
    
}
