package com.sparta.library.controller;

import com.sparta.library.dto.BookRequestDto;
import com.sparta.library.dto.BookResponseDto;
import com.sparta.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {
    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto requestDto) {
        BookResponseDto responseDto = bookService.createBook(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

    @GetMapping("/books")
    public ResponseEntity<List<BookResponseDto>> getBooks() {
        List<BookResponseDto> responseDto = bookService.findBooks();
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }
    @GetMapping("/book/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable Long id) {
        BookResponseDto responseDto = bookService.findBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);

    }

}
