package com.example.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String title;
    private String author;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        System.out.println("Hoàn tất khởi tạo Book");
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(String id) {
        this.id = id;
        System.out.println("Hoàn tất thiết lập mã sách");
    }

    public void setTitle(String title) {
        this.title = title;
        System.out.println("Hoàn tất thiết lập tiêu đề sách");
    }

    public void setAuthor(String author) {
        this.author = author;
        System.out.println("Hoàn tất thiết lập tác giả");
    }

    @Override
    public String toString() {
        return "Sách{" +
                "ID='" + id + '\'' +
                ", Tiêu đề='" + title + '\'' +
                ", Tác giả='" + author + '\'' +
                '}';
    }
}