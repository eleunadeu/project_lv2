package com.sparta.library.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class RentalRequestDto {
    private Long id;
    private Long book_id;
    private String title;
    private String author;
    private Long user_id;
    private String name;
    private String phoneNumber;
    private Boolean isReturned;
    private LocalDate createdAt;
    private LocalDate returnedAt;

}
