package model;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;

    public User() {
        try {
            // Constructor mac dinh
        } catch (Exception e) {
            System.out.println("Loi trong constructor mac dinh: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public User(String username, String password) {
        try {
            this.username = username;
            this.password = password;
        } catch (Exception e) {
            System.out.println("Loi trong constructor User: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getUsername() {
        try {
            return username;
        } catch (Exception e) {
            System.out.println("Loi khi lay username: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void setUsername(String username) {
        try {
            this.username = username;
        } catch (Exception e) {
            System.out.println("Loi khi dat username: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getPassword() {
        try {
            return password;
        } catch (Exception e) {
            System.out.println("Loi khi lay password: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void setPassword(String password) {
        try {
            this.password = password;
        } catch (Exception e) {
            System.out.println("Loi khi dat password: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        try {
            return "User{" + "username='" + username + '\'' + '}';
        } catch (Exception e) {
            System.out.println("Loi khi hien thi User: " + e.getMessage());
            e.printStackTrace();
            return "Thong tin User khong hop le";
        }
    }
}
