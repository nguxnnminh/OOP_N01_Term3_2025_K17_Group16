package model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;

    public User() {
        // Constructor mặc định
    }

    public User(String username, String password) {
        try {
            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên đăng nhập không được null hoặc rỗng");
            }
            if (password == null || password.trim().isEmpty()) {
                throw new IllegalArgumentException("Mật khẩu không được null hoặc rỗng");
            }
            this.username = username;
            this.password = password;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi tạo User: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất khởi tạo User");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        try {
            if (username == null || username.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên đăng nhập không được null hoặc rỗng");
            }
            this.username = username;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi thiết lập tên đăng nhập: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất thiết lập tên đăng nhập");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            if (password == null || password.trim().isEmpty()) {
                throw new IllegalArgumentException("Mật khẩu không được null hoặc rỗng");
            }
            this.password = password;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi thiết lập mật khẩu: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất thiết lập mật khẩu");
        }
    }

    @Override
    public String toString() {
        return "User{" + "tên đăng nhập='" + username + '\'' + '}';
    }
}