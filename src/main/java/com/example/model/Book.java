package com.example.model;

import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String title;
    private String author;

    public Book(String id, String title, String author) {
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã sách không được null hoặc rỗng");
            }
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Tiêu đề sách không được null hoặc rỗng");
            }
            if (author == null || author.trim().isEmpty()) {
                throw new IllegalArgumentException("Tác giả không được null hoặc rỗng");
            }
            this.id = id;
            this.title = title;
            this.author = author;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi tạo Book: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất khởi tạo Book");
        }
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
        try {
            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("Mã sách không được null hoặc rỗng");
            }
            this.id = id;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi thiết lập mã sách: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất thiết lập mã sách");
        }
    }

    public void setTitle(String title) {
        try {
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Tiêu đề sách không được null hoặc rỗng");
            }
            this.title = title;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi thiết lập tiêu đề sách: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất thiết lập tiêu đề sách");
        }
    }

    public void setAuthor(String author) {
        try {
            if (author == null || author.trim().isEmpty()) {
                throw new IllegalArgumentException("Tác giả không được null hoặc rỗng");
            }
            this.author = author;
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi thiết lập tác giả: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Hoàn tất thiết lập tác giả");
        }
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