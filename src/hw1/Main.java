/*
Name: Jonathan Gonzalez
Class: CSCI 350
Last update: 09/03/2024
Description: Create a library with multiple classes.
Classes:
    - Book
    - Magazine
    - Person
    - Library
 */
package hw1;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

@SuppressWarnings("SpellCheckingInspection")
public class Main {
    final String message = """
            Menu
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
            11. Person Center
            0. Exit
            """;

    final String personMessage = """
            1. Enter a new person
            2. Modified info
            3. Search for a person
            4. Books owned by a person
            5. Print menu
            6. Back to main menu
            """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        Main m = new Main();

        System.out.println(m.message);
        while (true) {
            int choice = m.menu(scanner);
            switch (choice) {
                case 1:
                    System.out.print(library);
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
                    System.out.println();
                    break;
                case 11:
                    m.personManager(library, scanner);
                    break;
                default:
                    System.out.println("Thanks for using CSCI350 Library!!!");
                    System.exit(0);
            }
        }
    }

    public int menu(Scanner scanner) {
        int choice = -1;

        while (choice == -1) {
            System.out.print("(MAIN) Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 0 || choice > 11) {
                System.out.println("Invalid option");
                choice = -1;
            }
        }

        return choice;
    }

    public void personManager(Library library, Scanner scanner) {
        System.out.println(personMessage);
        while (true) {
            System.out.print("(MANAGER) Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 6) {
                break;
            }

            if (choice < 1 || choice > 6) {
                System.out.println("Invalid option");
                continue;
            }

            switch (choice) {
                case 1:
                    personEntry(library, scanner);
                    break;
                case 2:
                    modifyPerson(library, scanner);
                    break;
                case 3:
                    searchPerson(library, scanner);
                    break;
                case 4:
                    ownedBy(library, scanner);
                case 5:
                    System.out.println(personMessage);
                default:
                    break;
            }
        }

    }

    public void personEntry(Library library, Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number (xxx-xxxx-xxx): ");
        String phone = scanner.nextLine();
        Person person = new Person(name, phone);

        library.addOwner(person);
    }

    public void modifyPerson(Library library, Scanner scanner) {
        System.out.print("Enter person ID: ");
        Integer personID = scanner.nextInt();
        scanner.nextLine();
        Person person = library.getOwnerInfo(personID);

        if (person != null) {
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new phone number (xxx-xxxx-xxx): ");
            String phoneNumber = scanner.nextLine();
            library.modifyOwner(personID, newName, phoneNumber);
        } else {
            System.out.println("Person not found");
        }
    }

    public void searchPerson(Library library, Scanner scanner) {
        System.out.print("Enter person name: ");
        String name = scanner.nextLine();
        ArrayList<Person> found = library.getOwnerInfo(name);
        if (found.isEmpty()) {
            System.out.println("Person not found");
        } else {
            System.out.println("List of people found: ");
            for (Person person : found) {
                System.out.println("    " + person.id + " " + person);
            }
        }
        System.out.println();
    }

    public void ownedBy(Library library, Scanner scanner) {
        System.out.print("Enter person ID: ");
        Integer personID = scanner.nextInt();
        scanner.nextLine();
        Person person = library.getOwnerInfo(personID);

        if (person != null) {
            library.ownedBy(person);
        } else {
            System.out.println("Person not found");
        }
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
        System.out.print("Book/Magazine ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Person ID: ");
        Integer personID = scanner.nextInt();
        scanner.nextLine();

        if (checkIn) {
            library.checkIn(type, id);
        } else {
            Person person = library.getOwnerInfo(personID);
            library.checkOut(type, id, person);
        }
    }

    public void search(Library library, Scanner scanner) {
        System.out.print("Title of item: ");
        String title = scanner.nextLine();
        library.searchBook(title);
    }
}
