// Import the Date class
import java.util.Date; 

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean available;
    private String borrowedBy; // New field to store the member who borrowed the book
    private Date borrowedDate; // New field to store the date when the book was borrowed

    // this is construictor run defaul by passing args
    public Book(String title, String author, String isbn, boolean available, String borrowedBy, Date borrowedDate) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
        this.borrowedBy = borrowedBy;
        this.borrowedDate = borrowedDate;
    }

    // Getters and setters for the fields
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    // toString method to print book details
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + available;
    }
}
