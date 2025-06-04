package src.test.java.thu;

import model.Book;

public class BookTest {
    public static void main(String[] args) {
        Book book = new Book("B001", "Lap trinh Java", "Nguyen Van A");

        System.out.println("=== Kiem thu Book ===");
        System.out.println("ID: " + book.getId());
        System.out.println("Tieu de: " + book.getTitle());
        System.out.println("Tac gia: " + book.getAuthor());
    }
}
