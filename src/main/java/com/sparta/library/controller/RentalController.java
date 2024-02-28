package com.sparta.library.controller;

import com.sparta.library.dto.RentalRequestDto;
import com.sparta.library.dto.RentalResponseDto;
import com.sparta.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RentalController {
    private final RentalService rentalService;

    @PostMapping("/rental")
    public ResponseEntity<Optional<RentalResponseDto>> createRental(@RequestBody RentalRequestDto requestDto) {
        RentalResponseDto responseDto = rentalService.createRental(requestDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "대출이 불가합니다."));
        return ResponseEntity.status(HttpStatus.OK).body(Optional.ofNullable(responseDto));

    }


    @PutMapping("/rental")
    public ResponseEntity<RentalResponseDto> updateRental(@RequestBody RentalRequestDto requestDto) {
        RentalResponseDto responseDto = rentalService.updateRental(requestDto).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "반납이 불가합니다."));
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

    @GetMapping("/rental")
    public ResponseEntity<List<RentalResponseDto>> getRental() {
        List<RentalResponseDto> responseDto = rentalService.findRental();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

}
