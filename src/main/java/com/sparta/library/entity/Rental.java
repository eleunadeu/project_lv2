package com.sparta.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "rental")
@EntityListeners(AuditingEntityListener.class)
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Column(nullable = false)
    private Boolean isReturned = true;


    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDate createdAt;

    @Column()
    private LocalDate returnedAt;


    public void update(Boolean isReturned) {
        this.isReturned = isReturned;
    }

    public void update(Boolean isReturned, LocalDate returnedAt) {
        this.isReturned = isReturned;
        this.returnedAt = returnedAt;

    }


}