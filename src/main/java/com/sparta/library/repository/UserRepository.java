package com.sparta.library.repository;

import com.sparta.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);

    Optional<User> findByIdNumber(String idNumber);

    Optional<User> findByPhoneNumber(String phoneNumber);
}
