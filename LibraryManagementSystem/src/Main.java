import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        library.loadFromFile("library.dat");

        boolean running = true;

        while (running) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add a book");
            System.out.println("2. Add a user");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. List books");
            System.out.println("6. List users");
            System.out.println("7. Save and Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    library.addBook(title, author);
                    System.out.println("Book added successfully.");
                    break;
                case 2:
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    library.addUser(name);
                    System.out.println("User added successfully.");
                    break;
                case 3:
                    System.out.print("Enter user name: ");
                    String borrowerName = scanner.nextLine();
                    User borrower = library.findUser(borrowerName);
                    if (borrower == null) {
                        System.out.println("User not found.");
                        break;
                    }
                    System.out.print("Enter book title: ");
                    String bookToBorrow = scanner.nextLine();
                    Book bookBorrow = library.findBook(bookToBorrow);
                    if (bookBorrow == null || !bookBorrow.isAvailable()) {
                        System.out.println("Book not available.");
                        break;
                    }
                    borrower.borrowBook(bookBorrow);
                    System.out.println("Book borrowed successfully.");
                    break;
                case 4:
                    System.out.print("Enter user name: ");
                    String returnerName = scanner.nextLine();
                    User returner = library.findUser(returnerName);
                    if (returner == null) {
                        System.out.println("User not found.");
                        break;
                    }
                    System.out.print("Enter book title: ");
                    String bookToReturn = scanner.nextLine();
                    Book bookReturn = library.findBook(bookToReturn);
                    if (bookReturn == null || bookReturn.isAvailable()) {
                        System.out.println("Book is not borrowed.");
                        break;
                    }
                    returner.returnBook(bookReturn);
                    System.out.println("Book returned successfully.");
                    break;
                case 5:
                    System.out.println("Listing all books:");
                    library.listBooks();
                    break;
                case 6:
                    System.out.println("Listing all users:");
                    library.listUsers();
                    break;
                case 7:
                    library.saveToFile("library.dat");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
        System.out.println("Thank you for using the Library Management System!");
    }
}