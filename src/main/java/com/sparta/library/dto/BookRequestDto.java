package com.sparta.library.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BookRequestDto {
    private Long book_id;
    private String title;
    private String author;
    private String language;
    private String publish;
    private String registerDate;
    private Boolean isLoaned;
}
