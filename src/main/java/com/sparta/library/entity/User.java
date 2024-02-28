package com.sparta.library.entity;

import com.sparta.library.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "user")
public class User {

    // 회원 정보
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String idNumber;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public User(UserRequestDto requestDto) {
        this.user_id = requestDto.getUser_id();
        this.name = requestDto.getName();
        this.sex = requestDto.getSex();
        this.idNumber = requestDto.getIdNumber();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.address = requestDto.getAddress();
        this.password = requestDto.getPassword();
        this.role = requestDto.getRole();
    }


}
