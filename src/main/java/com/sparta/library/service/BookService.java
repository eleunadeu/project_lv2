package com.sparta.library.service;

import com.sparta.library.dto.BookRequestDto;
import com.sparta.library.dto.BookResponseDto;
import com.sparta.library.entity.Book;
import com.sparta.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookResponseDto createBook(BookRequestDto requestDto) {
        Book book = new Book(requestDto);
        bookRepository.save(book);
        BookResponseDto responseDto = new BookResponseDto(book);
        return responseDto;
    }

    public List<BookResponseDto> findBooks() {
        return bookRepository.findAllByOrderByRegisterDateAsc().stream().map(BookResponseDto::new).toList();
    }


    public BookResponseDto findBook(Long id) {
        Book resBook = bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "선택한 책은 존재하지 않습니다."));
        BookResponseDto responseDto = new BookResponseDto(resBook);
        return responseDto;
    }

}
