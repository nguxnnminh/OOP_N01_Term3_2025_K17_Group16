package model;

import java.io.Serializable;
import java.time.LocalDate;

public class BorrowRecord implements Serializable {
    private Book book;
    private Reader reader;
    private LocalDate borrowDate;

    public BorrowRecord(Book book, Reader reader) {
        this.book = book;
        this.reader = reader;
        this.borrowDate = LocalDate.now(); // Ngày mượn là ngày hiện tại
    }

    // Getter
    public Book getBook() {
        return book;
    }

    public Reader getReader() {
        return reader;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    // Setter
    public void setBook(Book book) {
        this.book = book;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    // toString
    @Override
    public String toString() {
        return "BorrowRecord{" +
                "book=" + book +
                ", reader=" + reader +
                ", borrowDate=" + borrowDate +
                '}';
    }
}
