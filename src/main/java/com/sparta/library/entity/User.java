package com.sparta.library.entity;

import com.sparta.library.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String sex;

    @Column(nullable = false)
    private String idNumber;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Rental> rentalList = new ArrayList<>();


    public User(UserRequestDto requestDto) {
        this.user_id = requestDto.getUser_id();
        this.name = requestDto.getName();
        this.sex = requestDto.getSex();
        this.idNumber = requestDto.getIdNumber();
        this.phoneNumber = requestDto.getPhoneNumber();
        this.address = requestDto.getAddress();
        this.password = requestDto.getPassword();

    }


}
