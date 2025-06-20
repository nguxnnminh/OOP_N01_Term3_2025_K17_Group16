package com.example.model;

import java.io.Serializable;

public class BorrowRecord implements Serializable {
    private String id, bookId, readerId, borrowDate, returnDate;

    public BorrowRecord(String id, String bookId, String readerId, String borrowDate, String returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    // Getter
    public String getId() { return id; }
    public String getBookId() { return bookId; }
    public String getReaderId() { return readerId; }
    public String getBorrowDate() { return borrowDate; }
    public String getReturnDate() { return returnDate; }

    // Setter (Bổ sung để sửa lỗi bạn đang gặp)
    public void setId(String id) { this.id = id; }
    public void setBookId(String bookId) { this.bookId = bookId; }
    public void setReaderId(String readerId) { this.readerId = readerId; }
    public void setBorrowDate(String borrowDate) { this.borrowDate = borrowDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }

    // Nếu trong test có gọi toCSV()
    public String toCSV() {
        return id + "," + bookId + "," + readerId + "," + borrowDate + "," + returnDate;
    }

    @Override
    public String toString() {
        return "BorrowRecord{" +
                "id='" + id + '\'' +
                ", bookId='" + bookId + '\'' +
                ", readerId='" + readerId + '\'' +
                ", borrowDate='" + borrowDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
