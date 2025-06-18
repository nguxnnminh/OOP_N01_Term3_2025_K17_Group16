package test.java.thu;

import model.User;

public class UserTest {
    public static void main(String[] args) {
        User user = null;

        try {
            user = new User("admin", "123456");

            System.out.println("=== Kiem thu lop User ===");
            System.out.println("Ten dang nhap: " + user.getUsername());
            System.out.println("Mat khau: " + user.getPassword());

            // Test setter
            user.setPassword("newpass123");
            System.out.println("Mat khau moi: " + user.getPassword());

            // Test toString()
            System.out.println("Thong tin toString(): " + user.toString());

        } catch (Exception e) {
            System.out.println("❌ Đã xảy ra lỗi khi kiểm thử lớp User: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("✅ Kết thúc kiểm thử lớp User (khối finally).");
        }
    }
}
