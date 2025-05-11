package LibraryProject;

public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private boolean available;

    public Book(String id, String title, String author, String genre, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = year;
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

    public void borrow() { available = false; }
    public void returnBook() { available = true; }

    public String getId() { return id; }
    public boolean isAvailable() { return available; }

    @Override
    public String toString() {
        return title + " - " + author + " (" + publicationYear + ") [" + (available ? "Con" : "Da muon") + "]";
    }
}
