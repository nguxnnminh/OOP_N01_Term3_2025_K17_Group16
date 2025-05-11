package LibraryProject;

public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private boolean available;

    public Book(String id, String title, String author, String genre, int publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.available = true;
    }

    public boolean checkAvailability() {
        return available;
    }

    public void updateBookInfo(String title, String author, String genre, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = year;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isAvailable() { return available; }

    public void borrow() { this.available = false; }
    public void returnBook() { this.available = true; }

    public String toString() {
        return id + ". \"" + title + "\" by " + author + " (" + genre + ", " + publicationYear + ")" + (available ? " [Available]" : " [Borrowed]");
    }
}
