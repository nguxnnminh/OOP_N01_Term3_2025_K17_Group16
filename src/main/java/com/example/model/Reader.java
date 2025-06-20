package com.example.model;

import java.io.Serializable;

public class Reader implements Serializable {
    private String id;
    private String name;
    private String cccd;
    private String phone;
    private String birthDate;

    public Reader(String id, String name, String cccd, String phone, String birthDate) {
        this.id = id;
        this.name = name;
        this.cccd = cccd;
        this.phone = phone;
        this.birthDate = birthDate;
        System.out.println("Hoàn tất khởi tạo Reader");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCccd() {
        return cccd;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setId(String id) {
        this.id = id;
        System.out.println("Hoàn tất thiết lập mã độc giả");
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("Hoàn tất thiết lập tên độc giả");
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
        System.out.println("Hoàn tất thiết lập CCCD");
    }

    public void setPhone(String phone) {
        this.phone = phone;
        System.out.println("Hoàn tất thiết lập số điện thoại");
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
        System.out.println("Hoàn tất thiết lập ngày sinh");
    }

    @Override
    public String toString() {
        return id + ". " + name + " - CCCD: " + cccd + ", SĐT: " + phone + ", Ngày sinh: " + birthDate;
    }
}