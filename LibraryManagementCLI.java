import java.util.*;

// ==== Book Class ====
class Book {
    private int id;
    private String title;
    private String author;
    private boolean hasIssue; // Track if an issue is raised

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.hasIssue = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean hasIssue() { return hasIssue; }

    public void raiseIssue() {
        this.hasIssue = true;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + (hasIssue ? "Issue Raised" : "Available");
    }
}

// ==== User Class ====
class User {
    private int userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return userId + " | " + name;
    }
}

// ==== Library Class ====
class Library {
    private List<Book> books = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    // ===== Book Operations =====
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void listBooks(boolean onlyAvailable) {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        for (Book book : books) {
            if (!onlyAvailable || !book.hasIssue()) {
                System.out.println(book);
            }
        }
    }

    public void raiseIssue(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId && !book.hasIssue()) {
                book.raiseIssue();
                System.out.println("Issue raised for book: " + book.getTitle());
                return;
            }
        }
        System.out.println("Book not found or already has an issue.");
    }

    public void returnBook(int bookId) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId() == bookId && book.hasIssue()) {
                iterator.remove();
                System.out.println("Book returned and removed from library.");
                return;
            }
        }
        System.out.println("No book with issue found for given ID.");
    }

    // ===== User Operations =====
    public void addUser(User user) {
        users.add(user);
        System.out.println("User registered: " + user.getName());
    }

    public void deleteUser(int userId) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUserId() == userId) {
                iterator.remove();
                System.out.println("User deleted: " + user.getName());
                return;
            }
        }
        System.out.println("User not found.");
    }

    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        for (User user : users) {
            System.out.println(user);
        }
    }
}

// ==== Main CLI Class ====
public class LibraryManagementCLI {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Library library = new Library();
        int choice;

        do {
            System.out.println("\n==== Library Management Menu ====");
            System.out.println("1. Add Book");
            System.out.println("2. Raise an Issue with a Book");
            System.out.println("3. Return Book (delete from library if issue exists)");
            System.out.println("4. Add User");
            System.out.println("5. Delete User");
            System.out.println("6. List Books");
            System.out.println("7. List Users");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addBook(new Book(id, title, author));
                }
                case 2 -> {
                    System.out.println("Available Books:");
                    library.listBooks(true);
                    System.out.print("Enter Book ID to raise issue: ");
                    int id = sc.nextInt();
                    library.raiseIssue(id);
                }
                case 3 -> {
                    System.out.println("Books with issues:");
                    library.listBooks(false);
                    System.out.print("Enter Book ID to return: ");
                    int id = sc.nextInt();
                    library.returnBook(id);
                }
                case 4 -> {
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter User Name: ");
                    String name = sc.nextLine();
                    library.addUser(new User(userId, name));
                }
                case 5 -> {
                    System.out.print("Enter User ID to delete: ");
                    int userId = sc.nextInt();
                    library.deleteUser(userId);
                }
                case 6 -> library.listBooks(false);
                case 7 -> library.listUsers();
                case 8 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 8);
    }
          }
