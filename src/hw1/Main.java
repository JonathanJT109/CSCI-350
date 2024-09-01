package hw1;

// TODO: Compile program into a single file

import java.util.Optional;
import java.util.Scanner;

public class Main {
    String message = """
            Menu
            Options:
            1. Description of library
            2. Enter a new item (book, magazine)
            3. Search item
            4. Check-in an item
            5. Check-out an item
            6. Books in-stock
            7. Books on loan
            8. Print owners
            9. Print Menu
            10. Library Example (Load library with 5 books and 3 magazines)
            0. Exit
            """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        Main m = new Main();

        int choice = 0;
        System.out.println(m.message);
        while (true) {
            choice = m.menu(scanner);
            switch (choice) {
                case 1:
                    System.out.println(library);
                    break;
                case 2:
                    m.entry(library, scanner);
                    break;
                case 3:
                    m.search(library, scanner);
                    break;
                case 4:
                    m.processRequest(library, scanner, true);
                    break;
                case 5:
                    m.processRequest(library, scanner, false);
                    break;
                case 6:
                    library.stockBooks(true);
                    break;
                case 7:
                    library.stockBooks(false);
                    break;
                case 8:
                    library.bookOwner();
                    break;
                case 9:
                    System.out.println(m.message);
                    break;
                case 10:
                    library.fillLibrary();
                    break;
                default:
                    System.exit(0);
            }
        }
    }

    public int menu(Scanner scanner) {
        int choice = -1;

        while (choice == -1) {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 0 || choice > 10) {
                System.out.println("Invalid option");
                choice = -1;
            }
        }

        return choice;
    }

    public void entry(Library library, Scanner scanner) {
        System.out.print("Type (Book or Magazine): ");
        String type = scanner.nextLine().toLowerCase();
        if (type.equals("book")) {
            bookEntry(library);
        } else if (type.equals("magazine")) {
            magazineEntry(library);
        } else {
            throw new IllegalArgumentException("Invalid type");
        }
    }

    public void bookEntry(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        library.addBook(title, author, Optional.empty());
        System.out.println();
    }

    public void magazineEntry(Library library) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Date of Publication (mm/dd/yyyy): ");
        String date = scanner.nextLine();

        library.addMagazine(title, author, date, Optional.empty());
        System.out.println();
    }

    public void processRequest(Library library, Scanner scanner, boolean checkIn) {
        System.out.print("Type (Book or Magazine): ");
        String type = scanner.nextLine().toUpperCase();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (checkIn) {
            library.checkIn(type, id);
        } else {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter phone number: ");
            String phone = scanner.nextLine();
            library.checkOut(type, id, new Person(name, phone));
        }
    }

    public void search(Library library, Scanner scanner) {
        System.out.print("Title of item: ");
        String title = scanner.nextLine();
        library.searchBook(title);
    }
}
