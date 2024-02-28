package com.sparta.library.dto;

import com.sparta.library.entity.Rental;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RentalResponseDto {
    private Long book_id;
    private Long user_id;
    private Boolean isReturned;
    private LocalDate createdAt;
    private LocalDate returnedAt;

    public RentalResponseDto(Rental rental) {
        this.book_id = rental.getBookId();
        this.user_id = rental.getUserId();
        this.isReturned = rental.getIsReturned();
        this.createdAt = rental.getCreatedAt();
        this.returnedAt = rental.getReturnedAt();

    }
}
