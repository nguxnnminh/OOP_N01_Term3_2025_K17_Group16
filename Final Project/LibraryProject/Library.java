package LibraryProject;

import java.util.*;
import java.time.*;

// ======================== User.java ========================
public abstract class User {
    protected String userId;
    protected String fullName;
    protected String email;
    protected String phone;
    protected List<Book> borrowedBooks;

    public User(String userId, String fullName, String email, String phone) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    public abstract void register();

    public void borrowBook(Book book) {
        if (book.checkAvailability()) {
            book.borrow();
            borrowedBooks.add(book);
            System.out.println(fullName + " da muon sach: " + book.getTitle());
        } else {
            System.out.println("Sach khong co san.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.returnBook();
            System.out.println(fullName + " da tra sach: " + book.getTitle());
        } else {
            System.out.println("Khong tim thay sach trong danh sach da muon.");
        }
    }
}

// ======================== Reader.java ========================
package LibraryProject;

public class Reader extends User {
    public Reader(String userId, String fullName, String email, String phone) {
        super(userId, fullName, email, phone);
    }

    @Override
    public void register() {
        System.out.println("Doc gia " + fullName + " da dang ky thanh cong.");
    }
}

// ======================== BorrowRecord.java ========================
package LibraryProject;

import java.time.LocalDate;

public class BorrowRecord {
    private static int counter = 0;
    private String ticketId;
    private String userId;
    private String bookId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String status;

    public BorrowRecord(String userId, String bookId, int borrowDays) {
        this.ticketId = "TICKET" + (++counter);
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(borrowDays);
        this.returnDate = null;
        this.status = "Dang muon";
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
        this.status = "Da tra";
    }

    public void extendLoan(int extraDays) {
        dueDate = dueDate.plusDays(extraDays);
    }

    public String getTicketId() { return ticketId; }
    public String getUserId() { return userId; }
    public String getBookId() { return bookId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "[" + ticketId + "] " + userId + " muon sach " + bookId + " vao " + borrowDate +
                ", han tra: " + dueDate + ", trang thai: " + status;
    }
}

// ======================== Library.java ========================
package LibraryProject;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<BorrowRecord> records = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerUser(User user) {
        users.add(user);
        user.register();
    }

    public void borrow(String userId, String bookId, int days) {
        Book book = findBook(bookId);
        User user = findUser(userId);
        if (book != null && user != null && book.checkAvailability()) {
            user.borrowBook(book);
            records.add(new BorrowRecord(userId, bookId, days));
        } else {
            System.out.println("Muon khong thanh cong.");
        }
    }

    public void returnBook(String userId, String bookId) {
        Book book = findBook(bookId);
        User user = findUser(userId);
        if (book != null && user != null) {
            user.returnBook(book);
            for (BorrowRecord r : records) {
                if (r.getBookId().equals(bookId) && r.getUserId().equals(userId) && r.getStatus().equals("Dang muon")) {
                    r.returnBook();
                    break;
                }
            }
        }
    }

    public Book findBook(String id) {
        for (Book b : books) {
            if (b.getBookId().equals(id)) return b;
        }
        return null;
    }

    public User findUser(String id) {
        for (User u : users) {
            if (u.getUserId().equals(id)) return u;
        }
        return null;
    }

    public void showBooks() {
        for (Book b : books) System.out.println(b);
    }

    public void showRecords() {
        for (BorrowRecord r : records) System.out.println(r);
    }

    public void extendLoan(String ticketId, int extraDays) {
        for (BorrowRecord r : records) {
            if (r.getTicketId().equals(ticketId) && r.getStatus().equals("Dang muon")) {
                r.extendLoan(extraDays);
                System.out.println("Gia han thanh cong. Han moi: " + r.getDueDate());
                return;
            }
        }
        System.out.println("Khong tim thay phieu muon hop le.");
    }
}
