package com.example.repository;

import com.example.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, String> {
    List<Reader> findByNameContainingIgnoreCase(String name);
}
