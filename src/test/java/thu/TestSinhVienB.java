package thu;

import com.example.model.Book;
import com.example.model.BorrowRecord;

import service.Library;

public class TestSinhVienB {
    public static void main(String[] args) {
        Library library = new Library();

        try {
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

        } catch (Exception e) {
            System.out.println("❌ Đã xảy ra lỗi khi kiểm thử phương thức getBookInfoFromBorrowRecord:");
            e.printStackTrace();
        } finally {
            System.out.println("✅ Đã kết thúc kiểm thử TestSinhVienB (khối finally).");
        }
    }
}
