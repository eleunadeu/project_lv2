package com.sparta.library.dto;

import com.sparta.library.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long user_id;
    private String name;
    private String sex;
    private String idNumber;
    private String phoneNumber;
    private String address;
    private String password;

    public UserResponseDto(User user) {
        this.user_id = user.getUser_id();
        this.name = user.getName();
        this.sex = user.getSex();
        this.idNumber = user.getIdNumber();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.password = user.getPassword();
    }

}
