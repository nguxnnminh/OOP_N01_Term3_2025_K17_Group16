package model;

import java.io.Serializable;

public class BorrowRecord implements Serializable {
    private String id;
    private String bookId;
    private String readerId;
    private String borrowDate;
    private String returnDate;

    public BorrowRecord(String id, String bookId, String readerId, String borrowDate, String returnDate) {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã phiếu mượn không được null hoặc rỗng");
            }
            if (bookId == null || bookId.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã sách không được null hoặc rỗng");
            }
            if (readerId == null || readerId.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã người mượn không được null hoặc rỗng");
            }
            this.id = id;
            this.bookId = bookId;
            this.readerId = readerId;
            this.borrowDate = borrowDate;
            this.returnDate = returnDate;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi tạo BorrowRecord: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất khởi tạo BorrowRecord");
        }
    }

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

    public void setBorrowDate(String borrowDate) {
        try {
            this.borrowDate = borrowDate;
        } catch (Exception e) {
            System.err.println("Lỗi khi thiết lập ngày mượn: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất thiết lập ngày mượn");
        }
    }

    public void setReturnDate(String returnDate) {
        try {
            this.returnDate = returnDate;
        } catch (Exception e) {
            System.err.println("Lỗi khi thiết lập ngày trả: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất thiết lập ngày trả");
        }
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