package com.sparta.library.dto;

import com.sparta.library.entity.Rental;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RentalResponseDto {
    private Long id;
    private Long bookId;
    private Long userId;
    private String title;
    private String author;
    private String name;
    private String phoneNumber;
    private Boolean isReturned;
    private LocalDate createdAt;
    private LocalDate returnedAt;

    public RentalResponseDto(Rental rental) {
        this.id = rental.getId();
        this.bookId = rental.getBook().getBook_id();
        this.userId = rental.getUser().getUser_id();
        this.name = rental.getUser().getName();
        this.phoneNumber = rental.getUser().getPhoneNumber();
        this.title = rental.getBook().getTitle();
        this.author = rental.getBook().getAuthor();
        this.isReturned = rental.getIsReturned();
        this.createdAt = rental.getCreatedAt();
        this.returnedAt = rental.getReturnedAt();

    }
}
