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
        try {
            loadData();
        } catch (Exception e) {
            System.out.println("Lỗi khi load dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // CRUD cho Book
    public void addBook(Book book) {
        try {
            books.add(book);
            saveData();
            System.out.println("Đã thêm sách thành công!");
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm sách: " + e.getMessage());
            e.printStackTrace();
        }
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
        try {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getId().equals(updatedBook.getId())) {
                    books.set(i, updatedBook);
                    saveData();
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật sách: " + e.getMessage());
            e.printStackTrace();
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
            System.out.println("Lỗi khi xóa sách: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CRUD cho Reader
    public void addReader(Reader reader) {
        try {
            readers.add(reader);
            saveData();
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm độc giả: " + e.getMessage());
            e.printStackTrace();
        }
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
        try {
            for (int i = 0; i < readers.size(); i++) {
                if (readers.get(i).getId().equals(updatedReader.getId())) {
                    readers.set(i, updatedReader);
                    saveData();
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật độc giả: " + e.getMessage());
            e.printStackTrace();
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
            System.out.println("Lỗi khi xóa độc giả: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CRUD cho User
    public void addUser(User user) {
        try {
            users.add(user);
            saveData();
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm user: " + e.getMessage());
            e.printStackTrace();
        }
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
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(updatedUser.getUsername())) {
                    users.set(i, updatedUser);
                    saveData();
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật user: " + e.getMessage());
            e.printStackTrace();
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
            System.out.println("Lỗi khi xóa user: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // CRUD cho BorrowRecord
    public void addBorrowRecord(BorrowRecord record) {
        try {
            borrowRecords.add(record);
            saveData();
        } catch (Exception e) {
            System.out.println("Lỗi khi thêm phiếu mượn: " + e.getMessage());
            e.printStackTrace();
        }
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
        try {
            for (int i = 0; i < borrowRecords.size(); i++) {
                if (borrowRecords.get(i).getId().equals(updatedRecord.getId())) {
                    borrowRecords.set(i, updatedRecord);
                    saveData();
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật phiếu mượn: " + e.getMessage());
            e.printStackTrace();
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
            System.out.println("Lỗi khi xóa phiếu mượn: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // File IO
    @SuppressWarnings("unchecked")
    private void loadData() throws IOException, ClassNotFoundException {
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
        try {
            writeObject(books, "data/book.data");
            writeObject(readers, "data/reader.data");
            writeObject(users, "data/user.data");
            writeObject(borrowRecords, "data/borrow.data");
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
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
        } catch (IOException e) {
            System.out.println("Lỗi đọc file " + filename + ": " + e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi đọc file " + filename + ": " + e.getMessage());
            return null;
        }
    }
}
