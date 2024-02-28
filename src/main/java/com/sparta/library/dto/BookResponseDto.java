package com.sparta.library.dto;

import com.sparta.library.entity.Book;
import lombok.Getter;

@Getter
public class BookResponseDto {
    private Long book_id;
    private String title;
    private String author;
    private String language;
    private String publish;
    private String registerDate;
    private Boolean isLoaned;

    public BookResponseDto(Book book) {
        this.book_id = book.getBook_id();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.language = book.getLanguage();
        this.publish = book.getPublish();
        this.registerDate = book.getRegisterDate();
        this.isLoaned = book.getIsLoaned();

    }


}
