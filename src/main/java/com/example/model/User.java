package com.example.model;

public class User {
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên đăng nhập không được để trống");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Mật khẩu không được để trống");
        }
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
