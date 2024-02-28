package com.sparta.library.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfoDto {
    String username;
    String sex;
    String phoneNumber;
    String address;
    boolean isAdmin;
}
