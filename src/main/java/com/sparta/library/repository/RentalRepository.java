package com.sparta.library.repository;

import com.sparta.library.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    Optional<Rental> findFirstByBookIdOrderByUserIdDesc(Long id);

    List<Rental> findAllByOrderByCreatedAtAsc();

}