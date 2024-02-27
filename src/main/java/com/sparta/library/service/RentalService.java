package com.sparta.library.service;

import com.sparta.library.dto.RentalRequestDto;
import com.sparta.library.dto.RentalResponseDto;
import com.sparta.library.dto.UserRequestDto;
import com.sparta.library.dto.UserResponseDto;
import com.sparta.library.entity.Rental;
import com.sparta.library.entity.User;
import com.sparta.library.repository.RentalRepository;
import com.sparta.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;

    public RentalResponseDto createRental(RentalRequestDto requestDto) {
        Rental rental = new Rental(requestDto);
        rentalRepository.save(rental);
        return null;
    }

//    public UserResponseDto createUser(UserRequestDto requestDto) {
//        User user = new User(requestDto);
//        userRepository.save(user);
//        UserResponseDto responseDto = new UserResponseDto(user);
//        return responseDto;
//    }
//
//    public UserResponseDto findUser(Long id) {
//        User resUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 유저는 존재하지 않습니다."));
//        UserResponseDto responseDto = new UserResponseDto(resUser);
//        return responseDto;
//    }
}
