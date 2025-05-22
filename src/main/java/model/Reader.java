package model;

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

    @Override
    public String toString() {
        return id + ". " + name + " - CCCD: " + cccd + ", Phone: " + phone + ", DOB: " + birthDate;
    }
}

