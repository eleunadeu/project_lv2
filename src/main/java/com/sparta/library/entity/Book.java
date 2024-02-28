package com.sparta.library.entity;

import com.sparta.library.dto.BookRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long book_id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String language;

    @Column(nullable = false)
    private String publish;

    @Column(nullable = false)
    private String registerDate;

    private Boolean isLoaned = false;

    @OneToMany(mappedBy = "book")
    private List<Rental> rentalList = new ArrayList<>();


    public Book(BookRequestDto requestDto) {
        this.book_id = requestDto.getBook_id();
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.language = requestDto.getLanguage();
        this.publish = requestDto.getPublish();
        this.registerDate = requestDto.getRegisterDate();
        this.isLoaned = requestDto.getIsLoaned() == null ? false : requestDto.getIsLoaned();

    }


}
