package com.sparta.library.entity;

import com.sparta.library.dto.RentalRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", nullable = false)
    private Long book_id;

    @Column(name = "user_id", nullable = false)
    private Long user_id;


//    public Rental(RentalRequestDto requestDto) {
//        this.book_id=requestDto.
//
//    }
}