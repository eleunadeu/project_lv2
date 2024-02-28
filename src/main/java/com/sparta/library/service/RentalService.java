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
        Rental rental = new Rental();
        rental.setUser(resUser);
        rental.setBook(resBook);
        if (!rental.getIsReturned() || rental.getBook().getIsLoaned() || rental.getUser().getIsBorrowed()) {
            return Optional.empty();
        } else {
            rental.getBook().setIsLoaned(true);
            rental.getUser().setIsBorrowed(true);
            rental.update(false);
            rentalRepository.save(rental);
            return Optional.of(new RentalResponseDto(rental));

        }
    }


    @Transactional
    public Optional<RentalResponseDto> updateRental(RentalRequestDto requestDto) {
        Long bookId = requestDto.getBook_id();
        Long userId = requestDto.getUser_id();
        Book resBook = bookRepository.findById(bookId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "선택한 책은 존재하지 않습니다."));
        User resUser = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "선택한 회원은 존재하지 않습니다."));
        Rental resRental = rentalRepository.findFirstByBookAndUserOrderByIdDesc(resBook, resUser).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "선택한 정보가 존재하지 않습니다."));

        if (resRental.getIsReturned() || !resRental.getBook().getIsLoaned() || !resRental.getUser().getIsBorrowed()) {
            return Optional.empty();
        } else {
            resRental.getBook().setIsLoaned(false);
            resRental.getUser().setIsBorrowed(false);
            resRental.update(true, LocalDate.now());
            return Optional.of(new RentalResponseDto(resRental));

        }

    }

    public List<RentalResponseDto> findRental() {
        return rentalRepository.findAllByOrderByCreatedAtAsc().stream().map(RentalResponseDto::new).toList();
    }

}
