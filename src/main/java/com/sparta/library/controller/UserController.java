package com.sparta.library.controller;

import com.sparta.library.dto.SignupRequestDto;
import com.sparta.library.dto.UserInfoDto;
import com.sparta.library.security.UserDetailsImpl;
import com.sparta.library.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    //도서관 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signupPage() {
        return "signup";
    }

    //도서관 로그인 페이지
    @GetMapping("/user/login-page")
    public String loginPage() {
        return "login";
    }

    //도서관 회원가입 기능(회원 등록)
    @PostMapping("/user/signup")
    public String signup(@Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (fieldErrors.size() > 0) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
            }
            return "redirect:/api/user/signup";
        }

        userService.signup(requestDto);

        return "redirect:/api/user/login-page";
    }

    // 회원 관련 정보 받기
    @GetMapping("/user/{id}")
    @ResponseBody
    public UserInfoDto getUserdata(@PathVariable Long id) {
        return userService.getUserdata(id);
    }

    @GetMapping("/user-info")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 현재 인증된 사용자의 ID를 사용해 사용자 정보 조회
        return userService.getUserInfo(userDetails);
    }
}
