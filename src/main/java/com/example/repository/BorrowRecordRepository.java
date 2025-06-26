package com.example.repository;

import com.example.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, String> {
    List<BorrowRecord> findByBookId(String bookId);
    List<BorrowRecord> findByReaderId(String readerId);
}