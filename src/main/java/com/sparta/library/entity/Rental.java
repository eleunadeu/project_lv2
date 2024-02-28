package com.sparta.library.entity;

import com.sparta.library.dto.RentalRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "rental")
@EntityListeners(AuditingEntityListener.class)
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bookId")
    private Long bookId;

    @Column(name = "userId")
    private Long userId;

    @Column(nullable = false)
    private Boolean isReturned;


    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column()
    private LocalDate returnedAt;


    public Rental(RentalRequestDto requestDto) {
        this.id = getId();
        this.bookId = requestDto.getBook_id();
        this.userId = requestDto.getUser_id();
        this.isReturned = getIsReturned() == null ? true : getIsReturned();
        this.returnedAt = getReturnedAt();

    }

    public void update(Boolean isReturned) {
        this.isReturned = isReturned;
    }

    public void update(Boolean isReturned, LocalDate returnedAt) {
        this.isReturned = isReturned;
        this.returnedAt = returnedAt;

    }


}