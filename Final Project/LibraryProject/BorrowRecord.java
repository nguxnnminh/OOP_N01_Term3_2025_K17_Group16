package LibraryProject;

import java.time.LocalDate;

public class BorrowRecord {
    private String ticketId;
    private String userId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status; // "Dang muon" hoac "Da tra"

    public BorrowRecord(String ticketId, String userId, String bookId, LocalDate borrowDate, int loanDays) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.dueDate = borrowDate.plusDays(loanDays);
        this.status = "Dang muon";
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void returnBook(LocalDate returnDate) {
        this.returnDate = returnDate;
        this.status = "Da tra";
    }

    public void extendDueDate(int days) {
        this.dueDate = this.dueDate.plusDays(days);
    }

    @Override
    public String toString() {
        return "Phieu muon #" + ticketId + " | Nguoi muon: " + userId + " | Sach: " + bookId +
               " | Ngay muon: " + borrowDate + " | Han tra: " + dueDate +
               (returnDate != null ? " | Ngay tra: " + returnDate : "") +
               " | Trang thai: " + status;
    }
}
