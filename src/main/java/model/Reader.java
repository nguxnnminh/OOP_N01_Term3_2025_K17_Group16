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
            this.id = id;
            this.name = name;
            this.cccd = cccd;
            this.phone = phone;
            this.birthDate = birthDate;
        } catch (Exception e) {
            System.out.println("Loi khi tao Reader: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Getter
    public String getId() {
        try {
            return id;
        } catch (Exception e) {
            System.out.println("Loi khi lay ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String getName() {
        try {
            return name;
        } catch (Exception e) {
            System.out.println("Loi khi lay ten: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String getCccd() {
        try {
            return cccd;
        } catch (Exception e) {
            System.out.println("Loi khi lay CCCD: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String getPhone() {
        try {
            return phone;
        } catch (Exception e) {
            System.out.println("Loi khi lay so dien thoai: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String getBirthDate() {
        try {
            return birthDate;
        } catch (Exception e) {
            System.out.println("Loi khi lay ngay sinh: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Setter
    public void setName(String name) {
        try {
            this.name = name;
        } catch (Exception e) {
            System.out.println("Loi khi dat ten: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setCccd(String cccd) {
        try {
            this.cccd = cccd;
        } catch (Exception e) {
            System.out.println("Loi khi dat CCCD: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setPhone(String phone) {
        try {
            this.phone = phone;
        } catch (Exception e) {
            System.out.println("Loi khi dat so dien thoai: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setBirthDate(String birthDate) {
        try {
            this.birthDate = birthDate;
        } catch (Exception e) {
            System.out.println("Loi khi dat ngay sinh: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        try {
            return id + ". " + name + " - CCCD: " + cccd + ", Phone: " + phone + ", DOB: " + birthDate;
        } catch (Exception e) {
            System.out.println("Loi khi hien thi Reader: " + e.getMessage());
            e.printStackTrace();
            return "Thong tin khong hop le";
        }
    }
}
