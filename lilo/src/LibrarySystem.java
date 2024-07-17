import java.util.*;

public class LibrarySystem {
    private static final String LIBRARIAN_USERNAME = "rakesh";
    private static final String LIBRARIAN_PASSWORD = "admin";

    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        // Adding some members for testing
        library.addMember(new Member("shyam", "1234"));
        library.addMember(new Member("ramesh", "4567"));
        library.addMember(new Member("bhavy", "1010"));
        library.addMember(new Member("dhruv", "5678"));
        library.addMember(new Member("harsh", "2345"));
        library.addMember(new Member("vatsal", "9876"));

        while (true) {
            System.out.println("Welcome to the Library Management System!");
            System.out.println("1. Librarian Login");
            System.out.println("2. Member Login");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter librarian username: ");
                    String librarianUsername = sc.nextLine();
                    System.out.print("Enter librarian password: ");
                    String librarianPassword = sc.nextLine();

                    if (LIBRARIAN_USERNAME.equals(librarianUsername) && LIBRARIAN_PASSWORD.equals(librarianPassword)) {
                        System.out.println("Librarian login successful!");

                        while (true) {
                            System.out.println("1. Add Book");
                            System.out.println("2. Remove Book");
                            System.out.println("3. Display Books");
                            System.out.println("4. Display Available Books");
                            System.out.println("5. Display Borrowed Books");
                            System.out.println("6. Logout");
                            System.out.print("Please choose an option: ");
                            int librarianChoice = Integer.parseInt(sc.nextLine());

                            switch (librarianChoice) {
                                case 1:
                                    System.out.print("Enter book title: ");
                                    String title = sc.nextLine();
                                    System.out.print("Enter book author: ");
                                    String author = sc.nextLine();
                                    System.out.print("Enter book ISBN: ");
                                    String isbn = sc.nextLine();
                                    library.addBook(title, author, isbn);
                                    break;
                                case 2:
                                    System.out.print("Enter ISBN of the book to remove: ");
                                    String isbnToRemove = sc.nextLine();
                                    library.removeBook(isbnToRemove);
                                    break;
                                case 3:
                                    library.displayBooks();
                                    break;
                                case 4:
                                    library.displayAvailableBooks();
                                    break;
                                case 5:
                                    library.displayBorrowedBooks();
                                    break;
                                case 6:
                                    System.out.println("Logging out...");
                                    return;
                                default:
                                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                            }
                        }
                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                    break;
                case 2:
                    System.out.print("Enter member username: ");
                    String memberUsername = sc.nextLine();
                    System.out.print("Enter member password: ");
                    String memberPassword = sc.nextLine();

                    if (library.validateMember(memberUsername, memberPassword)) {
                        System.out.println("Member login successful!");

                        while (true) {
                            System.out.println("1. Borrow Book");
                            System.out.println("2. Return Book");
                            System.out.println("3. Display Available Books");
                            System.out.println("4. Logout");
                            System.out.print("Please choose an option: ");
                            int memberChoice = Integer.parseInt(sc.nextLine());

                            switch (memberChoice) {
                                case 1:
                                    System.out.print("Enter ISBN of the book to borrow: ");
                                    String isbnToBorrow = sc.nextLine();

                                    library.borrowBook(isbnToBorrow, memberUsername);
                                    break;
                                case 2:
                                    System.out.print("Enter ISBN of the book to return: ");
                                    String isbnToReturn = sc.nextLine();
                                    library.returnBook(isbnToReturn);
                                    break;
                                case 3:
                                    library.displayAvailableBooks();
                                    break;
                                case 4:
                                    System.out.println("Logging out...");
                                    return;
                                default:
                                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                            }
                        }
                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }
}
