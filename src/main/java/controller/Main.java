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
        try {
            Library library = new Library();

            // Đăng nhập hoặc đăng ký
            if (!loginMenu(library)) {
                System.out.println("Thoát chương trình.");
                return;
            }

            int luaChon;
            do {
                System.out.println("\n===== CHƯƠNG TRÌNH QUẢN LÝ THƯ VIỆN =====");
                System.out.println("1. Thêm sách");
                System.out.println("2. Hiển thị tất cả sách");
                System.out.println("3. Cập nhật sách");
                System.out.println("4. Xóa sách");

                System.out.println("5. Thêm người mượn");
                System.out.println("6. Hiển thị người mượn");
                System.out.println("7. Cập nhật người mượn");
                System.out.println("8. Xóa người mượn");

                System.out.println("9. Thêm tài khoản");
                System.out.println("10. Hiển thị tài khoản");
                System.out.println("11. Cập nhật tài khoản");
                System.out.println("12. Xóa tài khoản");

                System.out.println("13. Thêm phiếu mượn");
                System.out.println("14. Hiển thị phiếu mượn");
                System.out.println("15. Cập nhật phiếu mượn");
                System.out.println("16. Xóa phiếu mượn");

                System.out.println("0. Thoát");
                System.out.print("Nhập lựa chọn: ");
                luaChon = Integer.parseInt(sc.nextLine());

                switch (luaChon) {
                    case 1:
                        System.out.print("Nhập ID sách: ");
                        String id = sc.nextLine();
                        System.out.print("Nhập tên sách: ");
                        String ten = sc.nextLine();
                        System.out.print("Nhập tác giả: ");
                        String tacGia = sc.nextLine();
                        Book book = new Book(id, ten, tacGia);
                        library.addBook(book);
                        System.out.println("Thêm sách thành công.");
                        break;
                    case 2:
                        library.getAllBooks().forEach(System.out::println);
                        break;
                    case 3:
                        System.out.print("Nhập ID sách cần cập nhật: ");
                        String idUpdate = sc.nextLine();
                        Book bookUpdate = library.getBookById(idUpdate);
                        if (bookUpdate != null) {
                            System.out.print("Nhập tên mới: ");
                            bookUpdate.setTitle(sc.nextLine());
                            System.out.print("Nhập tác giả mới: ");
                            bookUpdate.setAuthor(sc.nextLine());
                            library.updateBook(bookUpdate);
                            System.out.println("Cập nhật thành công.");
                        } else {
                            System.out.println("Không tìm thấy sách.");
                        }
                        break;
                    case 4:
                        System.out.print("Nhập ID sách cần xóa: ");
                        if (library.deleteBook(sc.nextLine()))
                            System.out.println("Đã xóa.");
                        else
                            System.out.println("Không tìm thấy sách.");
                        break;
                    case 5:
                        System.out.print("Nhập ID người mượn: ");
                        String readerId = sc.nextLine();
                        System.out.print("Nhập họ tên: ");
                        String tenReader = sc.nextLine();
                        System.out.print("Nhập số điện thoại: ");
                        String sdt = sc.nextLine();
                        System.out.print("Nhập CCCD: ");
                        String cccd = sc.nextLine();
                        System.out.print("Nhập ngày sinh: ");
                        String ngaySinh = sc.nextLine();
                        Reader reader = new Reader(readerId, tenReader, sdt, cccd, ngaySinh);
                        library.addReader(reader);
                        System.out.println("Thêm người mượn thành công.");
                        break;
                    case 6:
                        library.getAllReaders().forEach(System.out::println);
                        break;
                    case 7:
                        System.out.print("Nhập ID người mượn cần cập nhật: ");
                        String readerUpdateId = sc.nextLine();
                        Reader readerUpdate = library.getReaderById(readerUpdateId);
                        if (readerUpdate != null) {
                            System.out.print("Nhập họ tên mới: ");
                            readerUpdate.setName(sc.nextLine());
                            System.out.print("Nhập số điện thoại mới: ");
                            readerUpdate.setPhone(sc.nextLine());
                            System.out.print("Nhập CCCD mới: ");
                            readerUpdate.setCccd(sc.nextLine());
                            System.out.print("Nhập ngày sinh mới: ");
                            readerUpdate.setBirthDate(sc.nextLine());
                            library.updateReader(readerUpdate);
                            System.out.println("Cập nhật thành công.");
                        } else {
                            System.out.println("Không tìm thấy người mượn.");
                        }
                        break;
                    case 8:
                        System.out.print("Nhập ID người mượn cần xóa: ");
                        if (library.deleteReader(sc.nextLine()))
                            System.out.println("Đã xóa.");
                        else
                            System.out.println("Không tìm thấy người mượn.");
                        break;
                    case 9:
                        System.out.print("Nhập tên đăng nhập: ");
                        String username = sc.nextLine();
                        System.out.print("Nhập mật khẩu: ");
                        String password = sc.nextLine();
                        User user = new User(username, password);
                        library.addUser(user);
                        System.out.println("Thêm tài khoản thành công.");
                        break;
                    case 10:
                        library.getAllUsers().forEach(System.out::println);
                        break;
                    case 11:
                        System.out.print("Nhập tên đăng nhập cần cập nhật: ");
                        String usernameUpdate = sc.nextLine();
                        User userUpdate = library.getUserByUsername(usernameUpdate);
                        if (userUpdate != null) {
                            System.out.print("Nhập mật khẩu mới: ");
                            userUpdate.setPassword(sc.nextLine());
                            library.updateUser(userUpdate);
                            System.out.println("Cập nhật thành công.");
                        } else {
                            System.out.println("Không tìm thấy tài khoản.");
                        }
                        break;
                    case 12:
                        System.out.print("Nhập tên đăng nhập cần xóa: ");
                        if (library.deleteUser(sc.nextLine()))
                            System.out.println("Đã xóa.");
                        else
                            System.out.println("Không tìm thấy tài khoản.");
                        break;
                    case 13:
                        System.out.print("Nhập ID phiếu mượn: ");
                        String borrowId = sc.nextLine();
                        System.out.print("Nhập ID sách: ");
                        String bookId = sc.nextLine();
                        System.out.print("Nhập ID người mượn: ");
                        String readerId2 = sc.nextLine();
                        System.out.print("Nhập ngày mượn: ");
                        String borrowDate = sc.nextLine();
                        System.out.print("Nhập ngày trả: ");
                        String returnDate = sc.nextLine();
                        BorrowRecord record = new BorrowRecord(borrowId, bookId, readerId2, borrowDate, returnDate);
                        library.addBorrowRecord(record);
                        System.out.println("Thêm phiếu mượn thành công.");
                        break;
                    case 14:
                        library.getAllBorrowRecords().forEach(System.out::println);
                        break;
                    case 15:
                        System.out.print("Nhập ID phiếu mượn cần cập nhật: ");
                        String borrowIdUpdate = sc.nextLine();
                        BorrowRecord recordUpdate = library.getBorrowRecordById(borrowIdUpdate);
                        if (recordUpdate != null) {
                            System.out.print("Nhập ngày mượn mới: ");
                            recordUpdate.setBorrowDate(sc.nextLine());
                            System.out.print("Nhập ngày trả mới: ");
                            recordUpdate.setReturnDate(sc.nextLine());
                            library.updateBorrowRecord(recordUpdate);
                            System.out.println("Cập nhật thành công.");
                        } else {
                            System.out.println("Không tìm thấy phiếu mượn.");
                        }
                        break;
                    case 16:
                        System.out.print("Nhập ID phiếu mượn cần xóa: ");
                        if (library.deleteBorrowRecord(sc.nextLine()))
                            System.out.println("Đã xóa.");
                        else
                            System.out.println("Không tìm thấy phiếu mượn.");
                        break;
                    case 0:
                        System.out.println("Kết thúc chương trình.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                        break;
                }
            } while (luaChon != 0);

        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ===================== ĐĂNG NHẬP / ĐĂNG KÝ ======================
    private static boolean loginMenu(Library library) {
        while (true) {
            System.out.println("===== ĐĂNG NHẬP HỆ THỐNG =====");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    if (login(library))
                        return true;
                    break;
                case "2":
                    register(library);
                    break;
                case "0":
                    return false;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại!");
            }
        }
    }

    private static boolean login(Library library) {
        while (true) {
            System.out.print("Tên đăng nhập: ");
            String username = sc.nextLine();
            System.out.print("Mật khẩu: ");
            String password = sc.nextLine();

            User user = library.getUserByUsername(username);
            if (user == null) {
                System.out.println("Tài khoản không tồn tại! Vui lòng thử lại hoặc chọn 0 để quay lại menu.");
            } else if (!user.getPassword().equals(password)) {
                System.out.println("Sai mật khẩu! Vui lòng thử lại hoặc chọn 0 để quay lại menu.");
            } else {
                System.out.println("Đăng nhập thành công!");
                return true;
            }

            System.out.print("Bạn có muốn thử lại? (y/n): ");
            String again = sc.nextLine().trim().toLowerCase();
            if (!again.equals("y"))
                return false;
        }
    }

    private static void register(Library library) {
        while (true) {
            System.out.print("Tên đăng nhập mới: ");
            String username = sc.nextLine();
            if (library.getUserByUsername(username) != null) {
                System.out.println("Tên đăng nhập đã tồn tại! Vui lòng nhập lại.");
                continue;
            }

            System.out.print("Mật khẩu: ");
            String password = sc.nextLine();
            User user = new User(username, password);
            library.addUser(user);
            System.out.println("Đăng ký thành công!");
            break;
        }
    }
}
