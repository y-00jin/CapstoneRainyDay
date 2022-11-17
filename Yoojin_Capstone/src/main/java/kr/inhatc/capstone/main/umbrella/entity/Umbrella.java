package kr.inhatc.capstone.main.umbrella.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Umbrella {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Mysql의 autoincrement
    @Column(name = "um_id", unique = true)
    private Long id;
    
    @Column(length = 20)
    private String umRentalState;             // 학번(아이디)
    
    @Column(nullable = false, length = 100) 
    private String password;                // 비밀번호
    
    
}
