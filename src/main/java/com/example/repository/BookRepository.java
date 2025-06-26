package com.example.repository;

import com.example.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    long countByIsBorrowedTrue();
}