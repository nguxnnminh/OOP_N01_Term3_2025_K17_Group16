package controller;

import model.Book;
import model.BorrowRecord;
import model.Reader;
import model.User;
import service.Library;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.loadData(); // load dữ liệu từ file
        Scanner sc = new Scanner(System.in);
        User currentUser = null;

        // === VÒNG ĐĂNG NHẬP / ĐĂNG KÝ ===
        while (currentUser == null) {
            System.out.println("===== HE THONG THU VIEN =====");
            System.out.println("1. Dang nhap");
            System.out.println("2. Dang ky");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Ten dang nhap: ");
                    String username = sc.nextLine();
                    System.out.print("Mat khau: ");
                    String password = sc.nextLine();
                    currentUser = library.authenticate(username, password);
                    if (currentUser != null) {
                        System.out.println("Dang nhap thanh cong! Xin chao " + currentUser.getUsername());
                    } else {
                        System.out.println("Sai ten dang nhap hoac mat khau.");
                    }
                    break;

                case "2":
                    System.out.print("Ten dang nhap moi: ");
                    String newUsername = sc.nextLine();
                    if (library.isUsernameTaken(newUsername)) {
                        System.out.println("Ten dang nhap da ton tai.");
                        break;
                    }
                    System.out.print("Mat khau: ");
                    String newPassword = sc.nextLine();
                    User newUser = new User(newUsername, newPassword);
                    library.addUser(newUser);
                    library.saveData();
                    System.out.println("Dang ky thanh cong!");
                    break;

                case "0":
                    System.out.println("Tam biet!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Lua chon khong hop le.");
            }
        }

        // === MENU THƯ VIỆN SAU ĐĂNG NHẬP ===
        int luaChon;
        do {
            System.out.println("\n=== MENU THU VIEN ===");
            System.out.println("1. Hien thi tat ca sach");
            System.out.println("2. Hien thi tat ca nguoi muon");
            System.out.println("3. Hien thi tat ca phieu muon");
            System.out.println("4. Them sach moi");
            System.out.println("5. Them nguoi muon moi");
            System.out.println("6. Tao phieu muon");
            System.out.println("7. Luu du lieu");
            System.out.println("8. Tim sach theo ID");
            System.out.println("9. Tim nguoi muon theo ID");
            System.out.println("0. Thoat");
            System.out.print("Nhap lua chon: ");

            luaChon = sc.nextInt();
            sc.nextLine();

            switch (luaChon) {
                case 1:
                    library.displayAllBooks();
                    break;
                case 2:
                    library.displayAllReaders();
                    break;
                case 3:
                    library.displayAllBorrowRecords();
                    break;
                case 4:
                    System.out.print("Nhap ID sach: ");
                    String idSach = sc.nextLine();
                    System.out.print("Nhap ten sach: ");
                    String tenSach = sc.nextLine();
                    System.out.print("Nhap ten tac gia: ");
                    String tacGia = sc.nextLine();
                    library.addBook(new Book(idSach, tenSach, tacGia));
                    break;
                case 5:
                    System.out.print("Nhap ID nguoi muon: ");
                    String idNguoi = sc.nextLine();
                    System.out.print("Nhap ten nguoi muon: ");
                    String tenNguoi = sc.nextLine();
                    System.out.print("Nhap so dien thoai: ");
                    String sdt = sc.nextLine();
                    System.out.print("Nhap so CCCD: ");
                    String cccd = sc.nextLine();
                    System.out.print("Nhap ngay sinh: ");
                    String ngaysinh = sc.nextLine();
                    library.addReader(new Reader(idNguoi, tenNguoi, sdt, cccd, ngaysinh));
                    break;
                case 6:
                    System.out.print("Nhap ID sach: ");
                    String idSachMuon = sc.nextLine();
                    System.out.print("Nhap ID nguoi muon: ");
                    String idNguoiMuon = sc.nextLine();
                    Book sach = library.findBookById(idSachMuon);
                    Reader nguoi = library.findReaderById(idNguoiMuon);
                    if (sach != null && nguoi != null) {
                        library.addBorrowRecord(new BorrowRecord(sach, nguoi));
                        System.out.println("Da tao phieu muon.");
                    } else {
                        System.out.println("Khong tim thay sach hoac nguoi muon.");
                    }
                    break;
                case 7:
                    library.saveData();
                    System.out.println("Da luu du lieu.");
                    break;
                case 8:
                    System.out.print("Nhap ID sach can tim: ");
                    String timSach = sc.nextLine();
                    Book timDuoc = library.findBookById(timSach);
                    if (timDuoc != null) {
                        System.out.println("Tim thay: " + timDuoc.getTitle() + " - " + timDuoc.getAuthor());
                    } else {
                        System.out.println("Khong tim thay sach.");
                    }
                    break;
                case 9:
                    System.out.print("Nhap ID nguoi muon can tim: ");
                    String timNguoi = sc.nextLine();
                    Reader nguoiTim = library.findReaderById(timNguoi);
                    if (nguoiTim != null) {
                        System.out.println("Tim thay: " + nguoiTim.getName() + " - " + nguoiTim.getPhone());
                    } else {
                        System.out.println("Khong tim thay nguoi muon.");
                    }
                    break;
                case 0:
                    System.out.println("Dang xuat. Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le.");
            }
        } while (luaChon != 0);

        sc.close();
    }
}