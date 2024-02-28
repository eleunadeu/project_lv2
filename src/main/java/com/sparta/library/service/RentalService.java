package com.sparta.library.service;

import com.sparta.library.dto.RentalRequestDto;
import com.sparta.library.dto.RentalResponseDto;
import com.sparta.library.entity.Book;
import com.sparta.library.entity.Rental;
import com.sparta.library.repository.BookRepository;
import com.sparta.library.repository.RentalRepository;
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

    @Transactional
    public Optional<RentalResponseDto> createRental(RentalRequestDto requestDto) {
        Long bookId = requestDto.getBook_id();
        Book resBook = bookRepository.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "선택한 책은 존재하지 않습니다."));
        Rental rental = new Rental(requestDto);
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
    public RentalResponseDto updateRental(Long id) {
        Rental resRental = rentalRepository.findFirstByBookIdOrderByUserIdDesc(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "선택한 책은 존재하지 않습니다."));
        System.out.println("resRental.getIsReturned() = " + resRental.getIsReturned());
        System.out.println("resRental.getUser_id() = " + resRental.getUserId());
        System.out.println("resRental.getBook_id() = " + resRental.getBookId());
        Book resBook = bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "선택한 책은 존재하지 않습니다."));
        resBook.update(false);
        resRental.update(true, LocalDate.now());
        RentalResponseDto responseDto = new RentalResponseDto(resRental);
        return responseDto;
    }

    public List<RentalResponseDto> findRental() {
        return rentalRepository.findAllByOrderByCreatedAtAsc().stream().map(RentalResponseDto::new).toList();
    }

}
