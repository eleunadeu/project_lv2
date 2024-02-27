package com.sparta.library.repository;
import com.sparta.library.entity.Rental;
import com.sparta.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental, Long> {

}
