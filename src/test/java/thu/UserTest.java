package src.test.java.thu;

import model.User;

public class UserTest {
    public static void main(String[] args) {
        User user = new User("admin", "123456");

        System.out.println("=== Kiem thu lop User ===");
        System.out.println("Ten dang nhap: " + user.getUsername());
        System.out.println("Mat khau: " + user.getPassword());

        // Test setter
        user.setPassword("newpass123");
        System.out.println("Mat khau moi: " + user.getPassword());

        // Test toString()
        System.out.println("Thong tin toString(): " + user.toString());
    }
}
