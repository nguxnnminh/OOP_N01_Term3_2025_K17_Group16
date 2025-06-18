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
        try {
            if (getBookById(book.getId()) != null) {
                System.out.println("Sách với ID này đã tồn tại.");
                return;
            }
            books.add(book);
            saveData();
            System.out.println("Đã thêm sách thành công.");
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm sách: " + e.getMessage());
            e.printStackTrace();
        } finally {
        }
    }

    public List<Book> getAllBooks() {
        try {
            return books;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return new ArrayList<>();
    }

    public Book getBookById(String id) {
        try {
            for (Book book : books) {
                if (book.getId().equals(id)) return book;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    public boolean updateBook(Book updatedBook) {
        try {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getId().equals(updatedBook.getId())) {
                    books.set(i, updatedBook);
                    saveData();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    public boolean deleteBook(String id) {
        try {
            Book book = getBookById(id);
            if (book != null) {
                books.remove(book);
                saveData();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    // CRUD cho Reader
    public void addReader(Reader reader) {
        try {
            if (getReaderById(reader.getId()) != null) {
                System.out.println("Độc giả với ID này đã tồn tại.");
                return;
            }
            readers.add(reader);
            saveData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public List<Reader> getAllReaders() {
        try {
            return readers;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return new ArrayList<>();
    }

    public Reader getReaderById(String id) {
        try {
            for (Reader reader : readers) {
                if (reader.getId().equals(id)) return reader;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    public boolean updateReader(Reader updatedReader) {
        try {
            for (int i = 0; i < readers.size(); i++) {
                if (readers.get(i).getId().equals(updatedReader.getId())) {
                    readers.set(i, updatedReader);
                    saveData();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    public boolean deleteReader(String id) {
        try {
            Reader reader = getReaderById(id);
            if (reader != null) {
                readers.remove(reader);
                saveData();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    // CRUD cho User
    public void addUser(User user) {
        try {
            if (getUserByUsername(user.getUsername()) != null) {
                System.out.println("Tên đăng nhập đã tồn tại.");
                return;
            }
            users.add(user);
            saveData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public List<User> getAllUsers() {
        try {
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return new ArrayList<>();
    }

    public User getUserByUsername(String username) {
        try {
            for (User user : users) {
                if (user.getUsername().equals(username)) return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    public boolean updateUser(User updatedUser) {
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(updatedUser.getUsername())) {
                    users.set(i, updatedUser);
                    saveData();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    public boolean deleteUser(String username) {
        try {
            User user = getUserByUsername(username);
            if (user != null) {
                users.remove(user);
                saveData();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    // CRUD cho BorrowRecord
    public void addBorrowRecord(BorrowRecord record) {
        try {
            if (getBorrowRecordById(record.getId()) != null) {
                System.out.println("Mã phiếu mượn đã tồn tại.");
                return;
            }
            borrowRecords.add(record);
            saveData();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public List<BorrowRecord> getAllBorrowRecords() {
        try {
            return borrowRecords;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return new ArrayList<>();
    }

    public BorrowRecord getBorrowRecordById(String id) {
        try {
            for (BorrowRecord record : borrowRecords) {
                if (record.getId().equals(id)) return record;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }

    public boolean updateBorrowRecord(BorrowRecord updatedRecord) {
        try {
            for (int i = 0; i < borrowRecords.size(); i++) {
                if (borrowRecords.get(i).getId().equals(updatedRecord.getId())) {
                    borrowRecords.set(i, updatedRecord);
                    saveData();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    public boolean deleteBorrowRecord(String id) {
        try {
            BorrowRecord record = getBorrowRecordById(id);
            if (record != null) {
                borrowRecords.remove(record);
                saveData();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return false;
    }

    // File IO
    @SuppressWarnings("unchecked")
    private void loadData() {
        try {
            Object objBooks = readObject("data/book.data");
            Object objReaders = readObject("data/reader.data");
            Object objUsers = readObject("data/user.data");
            Object objBorrow = readObject("data/borrow.data");

            books = (objBooks != null) ? (List<Book>) objBooks : new ArrayList<>();
            readers = (objReaders != null) ? (List<Reader>) objReaders : new ArrayList<>();
            users = (objUsers != null) ? (List<User>) objUsers : new ArrayList<>();
            borrowRecords = (objBorrow != null) ? (List<BorrowRecord>) objBorrow : new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    private void saveData() {
        try {
            writeObject(books, "data/book.data");
            writeObject(readers, "data/reader.data");
            writeObject(users, "data/user.data");
            writeObject(borrowRecords, "data/borrow.data");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    private void writeObject(Object obj, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(obj);
        } catch (IOException e) {
            System.out.println("Lỗi ghi file " + filename + ": " + e.getMessage());
            e.printStackTrace();
        } finally {
        }
    }

    private Object readObject(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Lỗi đọc file " + filename + ": " + e.getMessage());
        } finally {
        }
        return null;
    }

    public List<BorrowRecord> getNearDueBorrowRecords(int daysThreshold) {
        List<BorrowRecord> result = new ArrayList<>();
        try {
            LocalDate today = LocalDate.now();
            for (BorrowRecord record : borrowRecords) {
                LocalDate returnDate = LocalDate.parse(record.getReturnDate());
                long daysBetween = ChronoUnit.DAYS.between(today, returnDate);
                if (daysBetween >= 0 && daysBetween <= daysThreshold) {
                    result.add(record);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return result;
    }
}
