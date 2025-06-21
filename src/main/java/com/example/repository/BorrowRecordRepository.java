package com.example.repository;

import com.example.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, String> {
    List<BorrowRecord> findByBookIdAndReturnDateIsNull(String bookId);
}
