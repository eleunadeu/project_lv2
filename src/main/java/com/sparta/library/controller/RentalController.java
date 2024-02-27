package com.sparta.library.controller;

import com.sparta.library.dto.RentalRequestDto;
import com.sparta.library.dto.RentalResponseDto;
import com.sparta.library.dto.UserRequestDto;
import com.sparta.library.dto.UserResponseDto;
import com.sparta.library.service.RentalService;
import com.sparta.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("/rental")
    public ResponseEntity<RentalResponseDto> createRental(@RequestBody RentalRequestDto requestDto) {
        RentalResponseDto responseDto = rentalService.createRental(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }


//    @GetMapping("/user/{id}")
//    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
//        UserResponseDto responseDto = userService.findUser(id);
//        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
//
//    }

}
