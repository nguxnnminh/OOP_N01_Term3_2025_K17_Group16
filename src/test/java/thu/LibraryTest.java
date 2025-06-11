package test;

import model.Book;
import model.Reader;
import model.BorrowRecord;
import service.Library;

import java.time.LocalDate;
import java.util.List;

public class LibraryTest {
    public static void main(String[] args) {
        // Tạo instance thư viện
        Library library = new Library();

        // Thêm dữ liệu mẫu (bỏ qua nếu đã có sẵn data)
        library.addBook(new Book("B001", "Java Cơ Bản", "Nguyễn Văn A"));
        library.addBook(new Book("B002", "Lập Trình Web", "Trần Thị B"));

        library.addReader(new Reader("R001", "Nguyễn Văn C", "0123456789", "123456789", "1990-01-01"));
        library.addReader(new Reader("R002", "Lê Thị D", "0987654321", "987654321", "1992-02-02"));

        // Ngày mượn và ngày trả (sử dụng định dạng "yyyy-MM-dd")
        String today = LocalDate.now().toString();
        String futureDate = LocalDate.now().plusDays(7).toString();  // 7 ngày sau
        String pastDate = LocalDate.now().minusDays(5).toString();  // 5 ngày trước

        // Thêm phiếu mượn: 1 còn hiệu lực, 1 đã quá hạn
        library.addBorrowRecord(new BorrowRecord("BR001", "B001", "R001", today, futureDate));
        library.addBorrowRecord(new BorrowRecord("BR002", "B002", "R002", pastDate, today)); // hết hạn ngay hôm nay

        // Test phương thức lọc phiếu mượn còn hiệu lực
        List<BorrowRecord> activeBorrows = library.getActiveBorrowRecords();

        System.out.println("=== Danh sách phiếu mượn còn hiệu lực ===");
        for (BorrowRecord record : activeBorrows) {
            Book book = library.getBookById(record.getBookId());
            Reader reader = library.getReaderById(record.getReaderId());
            System.out.println("Phiếu: " + record.getId() 
                + " | Sách: " + (book != null ? book.getTitle() : "Không tìm thấy") 
                + " | Bạn đọc: " + (reader != null ? reader.getName() : "Không tìm thấy") 
                + " | Ngày mượn: " + record.getBorrowDate() 
                + " | Ngày trả: " + record.get
