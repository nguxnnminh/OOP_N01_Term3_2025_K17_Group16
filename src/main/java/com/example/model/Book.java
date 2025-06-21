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

    // Bắt buộc: constructor không tham số cho JPA
    public Book() {
    }

    // Constructor có 3 tham số
    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = "";
    }

    // Constructor có 4 tham số
    public Book(String id, String title, String author, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
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

    @Override
    public String toString() {
        return "Sách{" +
                "ID='" + id + '\'' +
                ", Tiêu đề='" + title + '\'' +
                ", Tác giả='" + author + '\'' +
                ", Thể loại='" + genre + '\'' +
                '}';
    }
}
