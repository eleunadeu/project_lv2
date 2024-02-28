package com.sparta.library.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserRequestDto {
    private Long user_id;
    private String name;
    private String sex;
    private String idNumber;
    private String phoneNumber;
    private String address;
    private String password;
    private Boolean isBorrowed;

}
