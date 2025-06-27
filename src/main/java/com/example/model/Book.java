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
    private int totalQuantity;  // Tổng số lượng sách ban đầu (quản lý theo đầu sách)

    public Book() {
    }

    public Book(String id, String title, String author, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.totalQuantity = 0;
    }

    public Book(String id, String title, String author, String genre, int totalQuantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.totalQuantity = totalQuantity;
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

    public String getGenre() {
        return genre;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

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

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public String toString() {
        return "Sách{" +
                "ID='" + id + '\'' +
                ", Tiêu đề='" + title + '\'' +
                ", Tác giả='" + author + '\'' +
                ", Thể loại='" + genre + '\'' +
                ", Tổng số lượng=" + totalQuantity +
                '}';
    }
}
