package com.sparta.library.dto;

import com.sparta.library.entity.Rental;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RentalResponseDto {
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

    public RentalResponseDto(Rental rental) {
        this.id = rental.getId();
        this.book_id = rental.getBookId();
        this.title = rental.getTitle();
        this.author = rental.getAuthor();
        this.user_id = rental.getUserId();
        this.name = rental.getName();
        this.phoneNumber = rental.getPhoneNumber();
        this.isReturned = rental.getIsReturned();
        this.createdAt = rental.getCreatedAt();
        this.returnedAt = rental.getReturnedAt();

    }
}
