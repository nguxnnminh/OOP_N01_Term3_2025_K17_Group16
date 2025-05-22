package service;

import model.Book;
import model.BorrowRecord;
import model.Reader;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
    private List<BorrowRecord> borrowRecords = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    // === Load / Save data ===
    public void loadData() {
        books = loadFromFile("data/book.data");
        readers = loadFromFile("data/reader.data");
        borrowRecords = loadFromFile("data/borrow.data");
        users = loadFromFile("data/user.data");
    }

    public void saveData() {
        saveToFile(books, "data/book.data");
        saveToFile(readers, "data/reader.data");
        saveToFile(borrowRecords, "data/borrow.data");
        saveToFile(users, "data/user.data");
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> loadFromFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists())
            return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Loi khi doc file: " + fileName);
            return new ArrayList<>();
        }
    }

    private <T> void saveToFile(List<T> list, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + fileName);
        }
    }

    // === User ===
    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username))
                return true;
        }
        return false;
    }

    public void addUser(User user) {
        users.add(user);
    }

    // === Book ===
    public void addBook(Book book) {
        books.add(book);
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("Chua co sach nao.");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book findBookById(String id) {
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(id))
                return book;
        }
        return null;
    }

    // === Reader ===
    public void addReader(Reader reader) {
        readers.add(reader);
    }

    public void displayAllReaders() {
        if (readers.isEmpty()) {
            System.out.println("Chua co nguoi muon nao.");
            return;
        }
        for (Reader reader : readers) {
            System.out.println(reader);
        }
    }

    public Reader findReaderById(String id) {
        for (Reader reader : readers) {
            if (reader.getId().equalsIgnoreCase(id))
                return reader;
        }
        return null;
    }

    // === BorrowRecord ===
    public void addBorrowRecord(BorrowRecord record) {
        borrowRecords.add(record);
    }

    public void displayAllBorrowRecords() {
        if (borrowRecords.isEmpty()) {
            System.out.println("Chua co phieu muon nao.");
            return;
        }
        for (BorrowRecord br : borrowRecords) {
            System.out.println(br);
        }
    }
}
