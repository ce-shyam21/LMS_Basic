import java.io.*;
import java.util.*;
//for the simpledate formate 
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Library {
    private ArrayList<Member> members;
    private ArrayList<Book> books;
    private static final String BOOKS_FILE = "src\\books.txt";

    public Library() {
        members = new ArrayList<>();
        books = new ArrayList<>();
        loadBooksFromFile(BOOKS_FILE);
    }

    // Add member to the library
    public void addMember(Member member) {
        members.add(member);
    }

    // Validate member login
    public boolean validateMember(String username, String password) {
        for (Member member : members) {
            if (member.getUsername().equals(username) && member.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Method to add a book to the library
    public void addBook(String title, String author, String isbn) {
        // Set the book as available and initialize the borrowedBy and borrowedDate
        // fields as null
        // first this is come here form the library.addBook(title, author, isbn);
        // then it going to Book.java
        Book book = new Book(title, author, isbn, true, null, null);
        books.add(book);
        saveBooksToFile(BOOKS_FILE);
        System.out.println("Book added successfully!");
    }

    // Method to remove a book from the library
    public void removeBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                books.remove(book);
                saveBooksToFile(BOOKS_FILE);
                System.out.println("Book removed successfully!");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Method to load books from file
    private void loadBooksFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                String title = parts[0];
                String author = parts[1];
                String isbn = parts[2];
                boolean available = Boolean.parseBoolean(parts[3]);
                String borrowedBy = null;
                Date borrowedDate = null;
                // if the length is >4 then it will go to the next part else return null
                if (parts.length > 4) {
                    borrowedBy = parts[4];
                    if (parts.length > 5 && !parts[5].equals("null")) {
                        borrowedDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(parts[5]);
                    }
                }
                books.add(new Book(title, author, isbn, available, borrowedBy, borrowedDate));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Method to save books to file
    private void saveBooksToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Book book : books) {
                writer.println(book.getTitle() + "," + book.getAuthor() + "," + book.getIsbn() + ","
                        + book.isAvailable() + "," + book.getBorrowedBy() + "," + book.getBorrowedDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to display all books in the library
    public void displayBooks() {
        System.out.println("Books in the library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Method to display available books
    public void displayAvailableBooks() {
        System.out.println("Available books in the library:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    // Method for member to borrow a book
    public void borrowBook(String isbn, String memberUsername) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && book.isAvailable()) {
                book.setAvailable(false);
                book.setBorrowedBy(memberUsername);
                book.setBorrowedDate(new Date()); // Set the borrowed date to the current date

                // Additional logging to check if the fields are being properly set
                System.out.println("Book borrowed successfully!");
                System.out.println("Borrowed by: " + book.getBorrowedBy());
                System.out.println("Borrowed date: " + book.getBorrowedDate());

                saveBooksToFile(BOOKS_FILE);
                return;
            }
        }
        System.out.println("Book not available or not found.");
    }

    // Method for member to return a book
    public void returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn) && !book.isAvailable()) {
                book.setAvailable(true);
                book.setBorrowedBy(null);
                book.setBorrowedDate(null);
                System.out.println("Book returned successfully!");
                saveBooksToFile(BOOKS_FILE);
                return;
            }
        }
        System.out.println("Book not found or already available.");
    }

    // Method to display borrowed books with member details
    public void displayBorrowedBooks() {
        System.out.println("Borrowed books in the library:");
        for (Book book : books) {
            if (!book.isAvailable()) {
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("ISBN: " + book.getIsbn());
                System.out.println("Borrowed by: " + book.getBorrowedBy());
                System.out.println("Borrowed date: " + book.getBorrowedDate());
                System.out.println();
            }
        }
    }
}
