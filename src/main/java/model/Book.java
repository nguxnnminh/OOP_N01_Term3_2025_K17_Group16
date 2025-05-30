package model;

import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String title;
    private String author;

    public Book(String id, String title, String author) {
        try {
            this.id = id;
            this.title = title;
            this.author = author;
        } catch (Exception e) {
            System.out.println("Loi khi tao Book: " + e.getMessage());
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

    public String getTitle() {
        try {
            return title;
        } catch (Exception e) {
            System.out.println("Loi khi lay tieu de sach: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String getAuthor() {
        try {
            return author;
        } catch (Exception e) {
            System.out.println("Loi khi lay ten tac gia: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Setter
    public void setId(String id) {
        try {
            this.id = id;
        } catch (Exception e) {
            System.out.println("Loi khi dat ID: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setTitle(String title) {
        try {
            this.title = title;
        } catch (Exception e) {
            System.out.println("Loi khi dat tieu de sach: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setAuthor(String author) {
        try {
            this.author = author;
        } catch (Exception e) {
            System.out.println("Loi khi dat ten tac gia: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // toString
    @Override
    public String toString() {
        try {
            return "Book{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    '}';
        } catch (Exception e) {
            System.out.println("Loi khi hien thi Book: " + e.getMessage());
            e.printStackTrace();
            return "Thong tin sach khong hop le";
        }
    }
}
