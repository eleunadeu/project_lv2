package com.sparta.library.service;

import com.sparta.library.dto.RentalRequestDto;
import com.sparta.library.dto.RentalResponseDto;
import com.sparta.library.entity.Book;
import com.sparta.library.entity.Rental;
import com.sparta.library.entity.User;
import com.sparta.library.repository.BookRepository;
import com.sparta.library.repository.RentalRepository;
import com.sparta.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Transactional
    public Optional<RentalResponseDto> createRental(RentalRequestDto requestDto) {
        Long bookId = requestDto.getBook_id();
        Long userId = requestDto.getUser_id();
        Book resBook = bookRepository.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "선택한 책은 존재하지 않습니다."));
        User resUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "선택한 유저는 존재하지 않습니다."));
        Rental rental = new Rental(requestDto, resBook, resUser);
        if (!rental.getIsReturned() || resBook.getIsLoaned()) {
            return Optional.empty();
        } else {
            resBook.update(true);
            rental.update(false);
            rentalRepository.save(rental);
            return Optional.of(new RentalResponseDto(rental));

        }
    }


    @Transactional
    public RentalResponseDto updateRental(RentalRequestDto requestDto) {
        Long bookId = requestDto.getBook_id();
        Long userId = requestDto.getUser_id();
        Rental resRental = rentalRepository.findByBookIdAndUserId(bookId, userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "선택한 책은 존재하지 않습니다."));
        Book resBook = bookRepository.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "선택한 책은 존재하지 않습니다."));
        resBook.update(false);
        resRental.update(true, LocalDate.now());
        RentalResponseDto responseDto = new RentalResponseDto(resRental);
        return responseDto;
    }

    public List<RentalResponseDto> findRental() {
        return rentalRepository.findAllByOrderByCreatedAtAsc().stream().map(RentalResponseDto::new).toList();
    }

}
