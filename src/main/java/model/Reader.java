package model;

import java.io.Serializable;

public class Reader implements Serializable {
    private String id;
    private String name;
    private String cccd;
    private String phone;
    private String birthDate;

    public Reader(String id, String name, String cccd, String phone, String birthDate) {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã độc giả không được null hoặc rỗng");
            }
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên độc giả không được null hoặc rỗng");
            }
            if (cccd == null || cccd.trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được null hoặc rỗng");
            }
            this.id = id;
            this.name = name;
            this.cccd = cccd;
            this.phone = phone;
            this.birthDate = birthDate;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi tạo Reader: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất khởi tạo Reader");
        }
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

    public void setName(String name) {
        try {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Tên độc giả không được null hoặc rỗng");
            }
            this.name = name;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi thiết lập tên độc giả: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất thiết lập tên độc giả");
        }
    }

    public void setCccd(String cccd) {
        try {
            if (cccd == null || cccd.trim().isEmpty()) {
                throw new IllegalArgumentException("CCCD không được null hoặc rỗng");
            }
            this.cccd = cccd;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi thiết lập CCCD: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất thiết lập CCCD");
        }
    }

    public void setPhone(String phone) {
        try {
            this.phone = phone;
        } catch (Exception e) {
            System.err.println("Lỗi khi thiết lập số điện thoại: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất thiết lập số điện thoại");
        }
    }

    public void setBirthDate(String birthDate) {
        try {
            this.birthDate = birthDate;
        } catch (Exception e) {
            System.err.println("Lỗi khi thiết lập ngày sinh: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất thiết lập ngày sinh");
        }
    }

    @Override
    public String toString() {
        return id + ". " + name + " - CCCD: " + cccd + ", SĐT: " + phone + ", Ngày sinh: " + birthDate;
    }
}