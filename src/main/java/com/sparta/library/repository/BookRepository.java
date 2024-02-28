package com.sparta.library.repository;
import com.sparta.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByOrderByRegisterDateAsc();

    Optional<Book> findById(Long id);

}
