package service;

import model.Book;
import model.Reader;
import model.User;
import model.BorrowRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Reader> readers = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<BorrowRecord> borrowRecords = new ArrayList<>();

    public Library() {
        loadData();
    }

    // CRUD for Book
    public void addBook(Book book) {
        books.add(book);
        saveData();
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(String id) {
        for (Book book : books)
            if (book.getId().equals(id))
                return book;
        return null;
    }

    public boolean updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(updatedBook.getId())) {
                books.set(i, updatedBook);
                saveData();
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(String id) {
        Book book = getBookById(id);
        if (book != null) {
            books.remove(book);
            saveData();
            return true;
        }
        return false;
    }

    // CRUD for Reader
    public void addReader(Reader reader) {
        readers.add(reader);
        saveData();
    }

    public List<Reader> getAllReaders() {
        return readers;
    }

    public Reader getReaderById(String id) {
        for (Reader reader : readers)
            if (reader.getId().equals(id))
                return reader;
        return null;
    }

    public boolean updateReader(Reader updatedReader) {
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i).getId().equals(updatedReader.getId())) {
                readers.set(i, updatedReader);
                saveData();
                return true;
            }
        }
        return false;
    }

    public boolean deleteReader(String id) {
        Reader reader = getReaderById(id);
        if (reader != null) {
            readers.remove(reader);
            saveData();
            return true;
        }
        return false;
    }

    // CRUD for User
    public void addUser(User user) {
        users.add(user);
        saveData();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserByUsername(String username) {
        for (User user : users)
            if (user.getUsername().equals(username))
                return user;
        return null;
    }

    public boolean updateUser(User updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(updatedUser.getUsername())) {
                users.set(i, updatedUser);
                saveData();
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String username) {
        User user = getUserByUsername(username);
        if (user != null) {
            users.remove(user);
            saveData();
            return true;
        }
        return false;
    }

    // CRUD for BorrowRecord
    public void addBorrowRecord(BorrowRecord record) {
        borrowRecords.add(record);
        saveData();
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecords;
    }

    public BorrowRecord getBorrowRecordById(String id) {
        for (BorrowRecord record : borrowRecords)
            if (record.getId().equals(id))
                return record;
        return null;
    }

    public boolean updateBorrowRecord(BorrowRecord updatedRecord) {
        for (int i = 0; i < borrowRecords.size(); i++) {
            if (borrowRecords.get(i).getId().equals(updatedRecord.getId())) {
                borrowRecords.set(i, updatedRecord);
                saveData();
                return true;
            }
        }
        return false;
    }

    public boolean deleteBorrowRecord(String id) {
        BorrowRecord record = getBorrowRecordById(id);
        if (record != null) {
            borrowRecords.remove(record);
            saveData();
            return true;
        }
        return false;
    }

    // File IO
    @SuppressWarnings("unchecked")
    private void loadData() {
        books = (List<Book>) readObject("data/book.data");
        readers = (List<Reader>) readObject("data/reader.data");
        users = (List<User>) readObject("data/user.data");
        borrowRecords = (List<BorrowRecord>) readObject("data/borrow.data");

        if (books == null)
            books = new ArrayList<>();
        if (readers == null)
            readers = new ArrayList<>();
        if (users == null)
            users = new ArrayList<>();
        if (borrowRecords == null)
            borrowRecords = new ArrayList<>();
    }

    private void saveData() {
        writeObject(books, "data/book.data");
        writeObject(readers, "data/reader.data");
        writeObject(users, "data/user.data");
        writeObject(borrowRecords, "data/borrow.data");
    }

    private void writeObject(Object obj, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Object readObject(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
