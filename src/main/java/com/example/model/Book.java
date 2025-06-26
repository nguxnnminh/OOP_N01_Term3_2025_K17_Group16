package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Book implements Serializable {

    @Id
    private String id;
    private String title;
    private String author;
    private String genre;
    private boolean isBorrowed; // Trạng thái sách: true (đã mượn), false (chưa mượn)

    // Constructor không tham số cho JPA
    public Book() {
    }

    // Constructor có 3 tham số
    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = "";
        this.isBorrowed = false;
    }

    // Constructor có 4 tham số
    public Book(String id, String title, String author, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isBorrowed = false;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setBorrowed(boolean borrowed) {
        this.isBorrowed = borrowed;
    }

    @Override
    public String toString() {
        return "Sách{" +
                "ID='" + id + '\'' +
                ", Tiêu đề='" + title + '\'' +
                ", Tác giả='" + author + '\'' +
                ", Thể loại='" + genre + '\'' +
                ", Trạng thái='" + (isBorrowed ? "Đã mượn" : "Chưa mượn") + '\'' +
                '}';
    }
}