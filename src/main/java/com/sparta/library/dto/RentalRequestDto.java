package com.sparta.library.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class RentalRequestDto {
    private Long book_id;
    private Long user_id;
    private Boolean isReturned;
    private LocalDate createdAt;
    private LocalDate returnedAt;

}
