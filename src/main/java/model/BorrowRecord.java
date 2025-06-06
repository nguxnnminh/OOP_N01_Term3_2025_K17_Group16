package model;

import java.io.Serializable;

public class BorrowRecord implements Serializable {
    private String id;
    private String bookId;
    private String readerId;
    private String borrowDate;
    private String returnDate;

    public BorrowRecord(String id, String bookId, String readerId, String borrowDate, String returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
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
    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Phiếu mượn{" +
                "ID='" + id + '\'' +
                ", Mã sách='" + bookId + '\'' +
                ", Mã người mượn='" + readerId + '\'' +
                ", Ngày mượn='" + borrowDate + '\'' +
                ", Ngày trả='" + returnDate + '\'' +
                '}';
    }
}
