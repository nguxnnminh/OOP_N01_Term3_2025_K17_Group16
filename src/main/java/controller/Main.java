package controller;

import model.Book;
import model.Reader;
import model.User;
import model.BorrowRecord;
import service.Library;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Library library = new Library();

        // Đăng nhập hoặc đăng ký
        if (!loginMenu(library)) {
            System.out.println("Thoat chuong trinh.");
            return;
        }

        int luaChon;
        do {
            System.out.println("\n===== CHUONG TRINH QUAN LY THU VIEN =====");
            System.out.println("1. Them sach");
            System.out.println("2. Hien thi tat ca sach");
            System.out.println("3. Cap nhat sach");
            System.out.println("4. Xoa sach");

            System.out.println("5. Them nguoi muon");
            System.out.println("6. Hien thi nguoi muon");
            System.out.println("7. Cap nhat nguoi muon");
            System.out.println("8. Xoa nguoi muon");

            System.out.println("9. Them tai khoan");
            System.out.println("10. Hien thi tai khoan");
            System.out.println("11. Cap nhat tai khoan");
            System.out.println("12. Xoa tai khoan");

            System.out.println("13. Them phieu muon");
            System.out.println("14. Hien thi phieu muon");
            System.out.println("15. Cap nhat phieu muon");
            System.out.println("16. Xoa phieu muon");

            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");
            luaChon = Integer.parseInt(sc.nextLine());

            switch (luaChon) {
                case 1:
                    System.out.print("Nhap ID sach: ");
                    String id = sc.nextLine();
                    System.out.print("Nhap ten sach: ");
                    String ten = sc.nextLine();
                    System.out.print("Nhap tac gia: ");
                    String tacGia = sc.nextLine();
                    Book book = new Book(id, ten, tacGia);
                    library.addBook(book);
                    System.out.println("Them sach thanh cong.");
                    break;
                case 2:
                    library.getAllBooks().forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Nhap ID sach can cap nhat: ");
                    String idUpdate = sc.nextLine();
                    Book bookUpdate = library.getBookById(idUpdate);
                    if (bookUpdate != null) {
                        System.out.print("Nhap ten moi: ");
                        bookUpdate.setTitle(sc.nextLine());
                        System.out.print("Nhap tac gia moi: ");
                        bookUpdate.setAuthor(sc.nextLine());
                        library.updateBook(bookUpdate);
                        System.out.println("Cap nhat thanh cong.");
                    } else {
                        System.out.println("Khong tim thay sach.");
                    }
                    break;
                case 4:
                    System.out.print("Nhap ID sach can xoa: ");
                    if (library.deleteBook(sc.nextLine()))
                        System.out.println("Da xoa.");
                    else
                        System.out.println("Khong tim thay sach.");
                    break;
                case 5:
                    System.out.print("Nhap ID nguoi muon: ");
                    String readerId = sc.nextLine();
                    System.out.print("Nhap ho ten: ");
                    String tenReader = sc.nextLine();
                    System.out.print("Nhap so dien thoai: ");
                    String sdt = sc.nextLine();
                    System.out.print("Nhap CCCD: ");
                    String cccd = sc.nextLine();
                    System.out.print("Nhap ngay sinh: ");
                    String ngaySinh = sc.nextLine();
                    Reader reader = new Reader(readerId, tenReader, sdt, cccd, ngaySinh);
                    library.addReader(reader);
                    System.out.println("Them nguoi muon thanh cong.");
                    break;
                case 6:
                    library.getAllReaders().forEach(System.out::println);
                    break;
                case 7:
                    System.out.print("Nhap ID nguoi muon can cap nhat: ");
                    String readerUpdateId = sc.nextLine();
                    Reader readerUpdate = library.getReaderById(readerUpdateId);
                    if (readerUpdate != null) {
                        System.out.print("Nhap ho ten moi: ");
                        readerUpdate.setName(sc.nextLine());
                        System.out.print("Nhap so dien thoai moi: ");
                        readerUpdate.setPhone(sc.nextLine());
                        System.out.print("Nhap CCCD moi: ");
                        readerUpdate.setCccd(sc.nextLine());
                        System.out.print("Nhap ngay sinh moi: ");
                        readerUpdate.setBirthDate(sc.nextLine());
                        library.updateReader(readerUpdate);
                        System.out.println("Cap nhat thanh cong.");
                    } else {
                        System.out.println("Khong tim thay nguoi muon.");
                    }
                    break;
                case 8:
                    System.out.print("Nhap ID nguoi muon can xoa: ");
                    if (library.deleteReader(sc.nextLine()))
                        System.out.println("Da xoa.");
                    else
                        System.out.println("Khong tim thay nguoi muon.");
                    break;
                case 9:
                    System.out.print("Nhap ten dang nhap: ");
                    String username = sc.nextLine();
                    System.out.print("Nhap mat khau: ");
                    String password = sc.nextLine();
                    User user = new User(username, password);
                    library.addUser(user);
                    System.out.println("Them tai khoan thanh cong.");
                    break;
                case 10:
                    library.getAllUsers().forEach(System.out::println);
                    break;
                case 11:
                    System.out.print("Nhap ten dang nhap can cap nhat: ");
                    String usernameUpdate = sc.nextLine();
                    User userUpdate = library.getUserByUsername(usernameUpdate);
                    if (userUpdate != null) {
                        System.out.print("Nhap mat khau moi: ");
                        userUpdate.setPassword(sc.nextLine());
                        library.updateUser(userUpdate);
                        System.out.println("Cap nhat thanh cong.");
                    } else {
                        System.out.println("Khong tim thay tai khoan.");
                    }
                    break;
                case 12:
                    System.out.print("Nhap ten dang nhap can xoa: ");
                    if (library.deleteUser(sc.nextLine()))
                        System.out.println("Da xoa.");
                    else
                        System.out.println("Khong tim thay tai khoan.");
                    break;
                case 13:
                    System.out.print("Nhap ID phieu muon: ");
                    String borrowId = sc.nextLine();
                    System.out.print("Nhap ID sach: ");
                    String bookId = sc.nextLine();
                    System.out.print("Nhap ID nguoi muon: ");
                    String readerId2 = sc.nextLine();
                    System.out.print("Nhap ngay muon: ");
                    String borrowDate = sc.nextLine();
                    System.out.print("Nhap ngay tra: ");
                    String returnDate = sc.nextLine();
                    BorrowRecord record = new BorrowRecord(borrowId, bookId, readerId2, borrowDate, returnDate);
                    library.addBorrowRecord(record);
                    System.out.println("Them phieu muon thanh cong.");
                    break;
                case 14:
                    library.getAllBorrowRecords().forEach(System.out::println);
                    break;
                case 15:
                    System.out.print("Nhap ID phieu muon can cap nhat: ");
                    String borrowIdUpdate = sc.nextLine();
                    BorrowRecord recordUpdate = library.getBorrowRecordById(borrowIdUpdate);
                    if (recordUpdate != null) {
                        System.out.print("Nhap ngay muon moi: ");
                        recordUpdate.setBorrowDate(sc.nextLine());
                        System.out.print("Nhap ngay tra moi: ");
                        recordUpdate.setReturnDate(sc.nextLine());
                        library.updateBorrowRecord(recordUpdate);
                        System.out.println("Cap nhat thanh cong.");
                    } else {
                        System.out.println("Khong tim thay phieu muon.");
                    }
                    break;
                case 16:
                    System.out.print("Nhap ID phieu muon can xoa: ");
                    if (library.deleteBorrowRecord(sc.nextLine()))
                        System.out.println("Da xoa.");
                    else
                        System.out.println("Khong tim thay phieu muon.");
                    break;
                case 0:
                    System.out.println("Ket thuc chuong trinh.");
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
                    break;
            }
        } while (luaChon != 0);
    }

    // ===================== ĐĂNG NHẬP / ĐĂNG KÝ ======================
    private static boolean loginMenu(Library library) {
        while (true) {
            System.out.println("===== DANG NHAP HE THONG =====");
            System.out.println("1. Dang nhap");
            System.out.println("2. Dang ky");
            System.out.println("0. Thoat");
            System.out.print("Lua chon: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    if (login(library))
                        return true; // Dang nhap thanh cong
                    break; // Neu that bai thi lap lai menu
                case "2":
                    register(library);
                    break; // Sau khi dang ky thi quay lai menu
                case "0":
                    return false;
                default:
                    System.out.println("Lua chon khong hop le. Vui long thu lai!");
            }
        }
    }

    private static boolean login(Library library) {
        while (true) {
            System.out.print("Ten dang nhap: ");
            String username = sc.nextLine();
            System.out.print("Mat khau: ");
            String password = sc.nextLine();

            User user = library.getUserByUsername(username);
            if (user == null) {
                System.out.println("Tai khoan khong ton tai! Vui long thu lai hoac chon 0 de quay lai menu.");
            } else if (!user.getPassword().equals(password)) {
                System.out.println("Sai mat khau! Vui long thu lai hoac chon 0 de quay lai menu.");
            } else {
                System.out.println("Dang nhap thanh cong!");
                return true;
            }

            System.out.print("Ban co muon thu lai? (y/n): ");
            String again = sc.nextLine().trim().toLowerCase();
            if (!again.equals("y"))
                return false;
        }
    }

    private static void register(Library library) {
        while (true) {
            System.out.print("Ten dang nhap moi: ");
            String username = sc.nextLine();
            if (library.getUserByUsername(username) != null) {
                System.out.println("Ten dang nhap da ton tai! Vui long nhap lai.");
                continue;
            }

            System.out.print("Mat khau: ");
            String password = sc.nextLine();
            User user = new User(username, password);
            library.addUser(user);
            System.out.println("Dang ky thanh cong!");
            break;
        }
    }
}
