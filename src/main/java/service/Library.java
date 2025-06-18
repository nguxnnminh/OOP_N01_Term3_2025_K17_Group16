package service;

import model.Book;
import model.Reader;
import model.User;
import model.BorrowRecord;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    // CRUD cho Book
    public void addBook(Book book) {
        if (getBookById(book.getId()) != null) {
            System.out.println("Sách với ID này đã tồn tại.");
            return;
        }
        books.add(book);
        saveData();
        System.out.println("Đã thêm sách thành công.");
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) return book;
        }
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

    // CRUD cho Reader
    public void addReader(Reader reader) {
        if (getReaderById(reader.getId()) != null) {
            System.out.println("Độc giả với ID này đã tồn tại.");
            return;
        }
        readers.add(reader);
        saveData();
    }

    public List<Reader> getAllReaders() {
        return readers;
    }

    public Reader getReaderById(String id) {
        for (Reader reader : readers) {
            if (reader.getId().equals(id)) return reader;
        }
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

    // CRUD cho User
    public void addUser(User user) {
        if (getUserByUsername(user.getUsername()) != null) {
            System.out.println("Tên đăng nhập đã tồn tại.");
            return;
        }
        users.add(user);
        saveData();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) return user;
        }
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

    // CRUD cho BorrowRecord
    public void addBorrowRecord(BorrowRecord record) {
        if (getBorrowRecordById(record.getId()) != null) {
            System.out.println("Mã phiếu mượn đã tồn tại.");
            return;
        }
        borrowRecords.add(record);
        saveData();
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecords;
    }

    public BorrowRecord getBorrowRecordById(String id) {
        for (BorrowRecord record : borrowRecords) {
            if (record.getId().equals(id)) return record;
        }
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
        Object objBooks = readObject("data/book.data");
        Object objReaders = readObject("data/reader.data");
        Object objUsers = readObject("data/user.data");
        Object objBorrow = readObject("data/borrow.data");

        books = (objBooks != null) ? (List<Book>) objBooks : new ArrayList<>();
        readers = (objReaders != null) ? (List<Reader>) objReaders : new ArrayList<>();
        users = (objUsers != null) ? (List<User>) objUsers : new ArrayList<>();
        borrowRecords = (objBorrow != null) ? (List<BorrowRecord>) objBorrow : new ArrayList<>();
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
            System.out.println("Lỗi ghi file " + filename + ": " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Object readObject(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi đọc file " + filename + ": " + e.getMessage());
            return null;
        }
    }
    public List<BorrowRecord> getNearDueBorrowRecords(int daysThreshold) {
        List<BorrowRecord> result = new ArrayList<>();
        LocalDate today = LocalDate.now();

        for (BorrowRecord record : borrowRecords) {
            LocalDate returnDate = LocalDate.parse(record.getReturnDate());
            long daysBetween = ChronoUnit.DAYS.between(today, returnDate);
            if (daysBetween >= 0 && daysBetween <= daysThreshold) {
             result.add(record);
            }
        }

        return result;
    }
   public int countBooksBorrowed(List<BorrowRecord> records) {
    int count = 0;
    for (BorrowRecord record : records) {
        if (record.getStatus().equals("chưa trả")) {
            count++;
        }
    }
    return count;
  }
}
