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
            this.id = id;
            this.bookId = bookId;
            this.readerId = readerId;
            this.borrowDate = borrowDate;
            this.returnDate = returnDate;
        } catch (Exception e) {
            System.out.println("Loi khi tao BorrowRecord: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Getter
    public String getId() {
        try {
            return id;
        } catch (Exception e) {
            System.out.println("Loi khi lay ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String getBookId() {
        try {
            return bookId;
        } catch (Exception e) {
            System.out.println("Loi khi lay bookId: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String getReaderId() {
        try {
            return readerId;
        } catch (Exception e) {
            System.out.println("Loi khi lay readerId: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String getBorrowDate() {
        try {
            return borrowDate;
        } catch (Exception e) {
            System.out.println("Loi khi lay borrowDate: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String getReturnDate() {
        try {
            return returnDate;
        } catch (Exception e) {
            System.out.println("Loi khi lay returnDate: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Setter
    public void setBorrowDate(String borrowDate) {
        try {
            this.borrowDate = borrowDate;
        } catch (Exception e) {
            System.out.println("Loi khi dat borrowDate: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setReturnDate(String returnDate) {
        try {
            this.returnDate = returnDate;
        } catch (Exception e) {
            System.out.println("Loi khi dat returnDate: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        try {
            return "BorrowRecord{" +
                    "ID='" + id + '\'' +
                    ", bookId='" + bookId + '\'' +
                    ", readerId='" + readerId + '\'' +
                    ", borrowDate='" + borrowDate + '\'' +
                    ", returnDate='" + returnDate + '\'' +
                    '}';
        } catch (Exception e) {
            System.out.println("Loi khi hien thi BorrowRecord: " + e.getMessage());
            e.printStackTrace();
            return "Thong tin BorrowRecord khong hop le";
        }
    }
}
