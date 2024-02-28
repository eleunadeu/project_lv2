package com.sparta.library.dto;

import com.sparta.library.entity.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String sex;
    @NotBlank
    private String idNumber;
    @NotBlank
    private String address;
    @NotBlank
    private String phoneNumber;

    private UserRoleEnum role;
    private boolean admin = false;
    private String adminToken = "";
}
