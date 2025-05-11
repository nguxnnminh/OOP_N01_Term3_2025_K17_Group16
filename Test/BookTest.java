package Test;

import LibraryProject.Book;

public class BookTest {
    public static void main(String[] args) {
        Book sach = new Book("B001", "De Men Phieu Luu Ky", "To Hoai", "Thieu nhi", 1941);

        System.out.println("=== KIEM THU LOP BOOK ===");

        // Kiem tra getter
        System.out.println("ID dung? " + sach.getId().equals("B001"));
        System.out.println("Tieu de dung? " + sach.getTitle().equals("De Men Phieu Luu Ky"));
        System.out.println("Tac gia dung? " + sach.getAuthor().equals("To Hoai"));
        System.out.println("The loai dung? " + sach.getGenre().equals("Thieu nhi"));
        System.out.println("Nam xuat ban dung? " + (sach.getPublicationYear() == 1941));
        System.out.println("Tinh trang ban dau (con)? " + sach.isAvailable());

        // Kiem tra muon sach
        sach.borrow();
        System.out.println("Tinh trang sau khi muon (da muon)? " + (!sach.isAvailable()));

        // Kiem tra tra sach
        sach.returnBook();
        System.out.println("Tinh trang sau khi tra (con)? " + sach.isAvailable());

        // Kiem tra cap nhat thong tin
        sach.updateBookInfo("Truyen ngan", 1950);
        System.out.println("Cap nhat the loai thanh cong? " + sach.getGenre().equals("Truyen ngan"));
        System.out.println("Cap nhat nam xuat ban thanh cong? " + (sach.getPublicationYear() == 1950));
    }
}
