package hw1;

import java.util.Optional;

// TODO: Search and see the status of a book by title
// TODO: Print number of books in stock
// TODO: Print number of books on loan
// TODO: Print phone numbers of current owners
// TODO: Compile program into a single file

public class Main {
    public static void main(String[] args) {
        Library lib = new Library();
        lib.addBook("Shadows of the Ancients", "Jonathan Gonzalez", Optional.empty());
        lib.addBook("Shadows of the Beginning", "Samuel Gonzalez", Optional.empty());
        lib.addMagazine("Biology", "Thomas Venecius", "02/15/2016", Optional.empty());
        lib.checkOut("book", 1, new Person("Miguel Ito", "623-3245-324"));
        lib.checkOut("magazine", 1, new Person("Miguel Ito", "623-3245-325"));
        lib.checkIn("magazine", 1);
        System.out.print(lib);
    }
}
