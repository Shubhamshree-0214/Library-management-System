import java.util.*;

class Book {
    private int id;
    private String title;
    private String author;
    private String category;
    private boolean available;

    public Book(int id, String title, String author, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = true;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return String.format("ID: %d | Title: %s | Author: %s | Category: %s | Available: %s",
                id, title, author, category, available ? "Yes" : "No");
    }
}

public class LibrarySystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<Integer, Book> books = new HashMap<>();
    private static int bookIdCounter = 1;

    public static void main(String[] args) {
        System.out.println("Welcome to the Library System");
        while (true) {
            System.out.println("\nSelect Role:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int roleChoice = Integer.parseInt(scanner.nextLine());

            switch (roleChoice) {
                case 1:
                    adminMenu();
                    break;
                case 2:
                    userMenu();
                    break;
                case 3:
                    System.out.println("Exiting... Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. View All Books");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    viewBooks();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. View Books");
            System.out.println("2. Issue Book");
            System.out.println("3. Return Book");
            System.out.println("4. Back to Main Menu");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    viewBooks();
                    break;
                case 2:
                    issueBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();

        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        Book book = new Book(bookIdCounter++, title, author, category);
        books.put(book.getId(), book);
        System.out.println("Book added successfully: " + book);
    }

    private static void updateBook() {
        System.out.print("Enter book ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        Book book = books.get(id);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        System.out.print("Enter new title (leave blank to keep '" + book.getTitle() + "'): ");
        String title = scanner.nextLine();
        if (!title.isEmpty()) {
            book = new Book(book.getId(), title, book.getAuthor(), book.getCategory());
            books.put(id, book);
        }

        System.out.print("Enter new author (leave blank to keep '" + book.getAuthor() + "'): ");
        String author = scanner.nextLine();
        if (!author.isEmpty()) {
            book = new Book(book.getId(), book.getTitle(), author, book.getCategory());
            books.put(id, book);
        }

        System.out.print("Enter new category (leave blank to keep '" + book.getCategory() + "'): ");
        String category = scanner.nextLine();
        if (!category.isEmpty()) {
            book = new Book(book.getId(), book.getTitle(), book.getAuthor(), category);
            books.put(id, book);
        }

        System.out.println("Book updated successfully: " + books.get(id));
    }

    private static void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (books.remove(id) != null) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\n--- Books List ---");
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    private static void issueBook() {
        System.out.print("Enter book ID to issue: ");
        int id = Integer.parseInt(scanner.nextLine());

        Book book = books.get(id);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("Book is currently issued to someone else.");
            return;
        }
        book.setAvailable(false);
        System.out.println("Book issued successfully: " + book.getTitle());
    }

    private static void returnBook() {
        System.out.print("Enter book ID to return: ");
        int id = Integer.parseInt(scanner.nextLine());

        Book book = books.get(id);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (book.isAvailable()) {
            System.out.println("This book was not issued.");
            return;
        }
        book.setAvailable(true);
        System.out.println("Book returned successfully: " + book.getTitle());
    }
}