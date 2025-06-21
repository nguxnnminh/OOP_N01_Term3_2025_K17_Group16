package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class BorrowRecord implements Serializable {

    @Id
    private String id;

    private String bookId;
    private String readerId;
    private String borrowDate;
    private String returnDate;

    // Constructor mặc định bắt buộc (JPA cần)
    public BorrowRecord() {
    }

    // Constructor đầy đủ
    public BorrowRecord(String id, String bookId, String readerId, String borrowDate, String returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        System.out.println("Hoàn tất khởi tạo BorrowRecord");
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getBookId() {
        return bookId;
    }

    public String getReaderId() {
        return readerId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    // Setter
    public void setId(String id) {
        this.id = id;
        System.out.println("Hoàn tất thiết lập ID phiếu mượn");
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
        System.out.println("Hoàn tất thiết lập ID sách");
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
        System.out.println("Hoàn tất thiết lập ID độc giả");
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
        System.out.println("Hoàn tất thiết lập ngày mượn");
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
        System.out.println("Hoàn tất thiết lập ngày trả");
    }

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
