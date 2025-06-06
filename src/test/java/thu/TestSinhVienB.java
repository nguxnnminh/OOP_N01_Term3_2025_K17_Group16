package test.java.thu;

import model.Book;
import model.BorrowRecord;
import service.Library;

public class TestSinhVienB {
public static void main(String[] args) {
Library library = new Library();

// Tạo giả lập 1 phiếu mượn
BorrowRecord record = new BorrowRecord("PM01", "S01", "DG01", "2025-06-01", "2025-06-10");
Book book = library.getBookInfoFromBorrowRecord(record);

if (book != null) {
System.out.println("Thông tin sách:");
System.out.println("Mã sách: " + book.getId());
System.out.println("Tên sách: " + book.getTitle());
System.out.println("Tác giả: " + book.getAuthor());
} else {
System.out.println("Không tìm thấy sách.");
        }
    }
}