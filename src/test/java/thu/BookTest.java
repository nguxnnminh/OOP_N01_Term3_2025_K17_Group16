package thu;

import com.example.model.Book;

public class BookTest {
    public static void main(String[] args) {
        try {
            Book book = new Book("B001", "Lap trinh Java", "Nguyen Van A");

            System.out.println("=== Kiem thu Book ===");
            System.out.println("ID: " + book.getId());
            System.out.println("Tieu de: " + book.getTitle());
            System.out.println("Tac gia: " + book.getAuthor());
        } catch (Exception e) {
            System.out.println("Da xay ra loi: " + e.getMessage());
            e.printStackTrace(); // In stack trace để dễ debug nếu cần
        } finally {
            System.out.println("Ket thuc kiem thu lop Book.");
        }
    }
}
