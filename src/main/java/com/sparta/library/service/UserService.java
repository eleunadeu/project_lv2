package com.sparta.library.service;

import com.sparta.library.dto.SignupRequestDto;
import com.sparta.library.dto.UserInfoDto;
import com.sparta.library.entity.User;
import com.sparta.library.entity.UserRoleEnum;
import com.sparta.library.repository.UserRepository;
import com.sparta.library.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "";

    // 회원 가입 기능
    public void signup(SignupRequestDto requestDto) {
        // 중복 검사 없는 회원 정보
        String name = requestDto.getName();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String sex = requestDto.getSex();
        String address = requestDto.getAddress();

        // 주민번호 중복 확인
        String idNumber = requestDto.getIdNumber();
        Optional<User> checkIdNumber = userRepository.findByIdNumber(idNumber);
        if (checkIdNumber.isPresent()) {
            throw new IllegalArgumentException("중복된 주민번호가 존재합니다.");
        }

        // 전화 번호 중복 확인
        String phoneNumber = requestDto.getPhoneNumber();
        Optional<User> checkPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if (checkPhoneNumber.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(name, password, sex, idNumber, phoneNumber, address, role);
        userRepository.save(user);
    }

    public UserInfoDto getUserInfo(Long id, UserDetailsImpl userDetails) {
        // 회원여부 확인
        User user = userRepository.findById(userDetails.getUser().getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (user.getUser_id() == id) {
            // 회원 아이디 기준으로 회원 정보 가져오기
            return UserInfoDto.builder()
                    .name(user.getName())
                    .sex(user.getSex())
                    .phoneNumber(user.getPhoneNumber())
                    .address(user.getAddress())
                    .isAdmin(user.getRole() == UserRoleEnum.ADMIN)
                    .build();
        } else {
            System.out.println("다른 유저의 정보는 확인할 수 없습니다.");
            return UserInfoDto.builder().build(); // 빈 빌더로 기본값을 가진 객체를 생성
        }
    }
}
