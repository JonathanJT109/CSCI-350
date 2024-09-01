package hw1;

import java.util.*;

// TODO: Extra credit (Maybe?)

public class Library {
    private static Integer nextId = 1;
    private final Integer id;
    private Integer book_id = 1;
    private Integer magazine_id = 1;
    public String name;

    public ArrayList<Book> books = new ArrayList<>();
    public ArrayList<Magazine> magazines = new ArrayList<>();

    public Library() {
        this.id = nextId++;
    }

    public Integer getId() {
        return id;
    }

    public void addBook(String title, String author, Optional<Person> current_owner) {
        Book book;

        book = current_owner.map(person -> new Book(title, author, book_id++, person)).orElseGet(() -> new Book(title, author, book_id++));

        books.add(book);
        System.out.println("Successfully added book " + book.getTitle());
    }

    public void addMagazine(String title, String author, String data_publication, Optional<Person> current_owner) {
        Magazine magazine;

        magazine = current_owner.map(person -> new Magazine(title, author, magazine_id++, data_publication, person)).orElseGet(() -> new Magazine(title, author, magazine_id++, data_publication));

        magazines.add(magazine);
        System.out.println("Successfully added book " + magazine.getTitle());
    }

    public void checkIn(String type, Integer id) {
        if (type.equalsIgnoreCase("book")) {
            Book book = books.stream()
                    .filter(b -> Objects.equals(b.getId(), id))
                    .findFirst()
                    .orElseThrow(() -> new IndexOutOfBoundsException("Book does not exist"));
            book.removeCurrentOwner();
        } else if (type.equalsIgnoreCase("magazine")) {
            Magazine magazine = magazines.stream()
                    .filter(b -> Objects.equals(b.getId(), id))
                    .findFirst()
                    .orElseThrow(() -> new IndexOutOfBoundsException("Book does not exist"));
            magazine.removeCurrentOwner();
        } else {
            throw new IllegalArgumentException("Unsupported book type");
        }

        System.out.println("Check-in Successfully completed");

    }

    public void checkOut(String type, Integer id, Person person) {
        if (type.equalsIgnoreCase("book")) {
            Book book = books.stream()
                    .filter(b -> Objects.equals(b.getId(), id))
                    .findFirst()
                    .orElseThrow(() -> new IndexOutOfBoundsException("Book does not exist"));
            book.setCurrentOwner(person);
        } else if (type.equalsIgnoreCase("magazine")) {
            Magazine magazine = magazines.stream()
                    .filter(b -> Objects.equals(b.getId(), id))
                    .findFirst()
                    .orElseThrow(() -> new IndexOutOfBoundsException("Book does not exist"));
            magazine.setCurrentOwner(person);
        } else {
            throw new IllegalArgumentException("Unsupported book type");
        }

        String output = (type.equals("book") ? "Assigning book" : "Assigning magazine") + " ";
        System.out.println(output + id + " to " + person);
    }

    public void searchBook(String title) {
        ArrayList<Book> combined = new ArrayList<>();
        combined.addAll(books);
        combined.addAll(magazines);
        Book book = combined.stream().filter(b -> Objects.equals(b.getTitle().toLowerCase(), title.toLowerCase())).findFirst().orElse(null);
        if (book != null) {
            System.out.println("Book Found:");
            System.out.println(book);
        } else {
            System.out.println("Book Not Found");
        }
    }

    public void stockBooks(boolean stock) {
        ArrayList<Book> inStock = new ArrayList<>();

        for (Book book : books) {
            if ((!stock && book.getCurrentOwner() != null) || (stock && book.getCurrentOwner() == null)) {
                inStock.add(book);
            }
        }

        for (Book book : magazines) {
            if ((!stock && book.getCurrentOwner() != null) || (stock && book.getCurrentOwner() == null)) {
                inStock.add(book);
            }
        }

        String message = (stock) ? "Books in stock: " : "Books out of stock: ";
        if (stock) {
            System.out.println("-------- Books In Stock --------");
        } else {
            System.out.println("------ Books Out Of Stock ------");
        }
        System.out.println(message + inStock.size());
        for (Book book : inStock) {
            System.out.println(book);
        }
    }

    public void bookOwner() {
        ArrayList<Book> combined = new ArrayList<>();
        ArrayList<Person> owners = new ArrayList<>();

        combined.addAll(books);
        combined.addAll(magazines);

        for (Book book : combined) {
            if (book.getCurrentOwner() != null) {
                owners.add(book.getCurrentOwner());
            }
        }

        System.out.println("---------- Book Owner ----------");
        for (Person owner : owners) {
            System.out.println(owner);
        }
        System.out.println();
    }

    public void fillLibrary() {
        String[] authors = {"Lewis Truman", "Camila Robinson", "Wilson Stuart"};
        String[] titles = {"Whispers of the Forgotten", "Echoes in the Abyss", "The Last Enigma", "Journey Through the Veil", "The Clockwork Conundrum", "Shadows of the Ancients", "A Symphony of Stars", "The Hidden Manuscript", "Beyond the Horizon's Edge", "The Secrets of Avalon"};

        Random random = new Random();

        for (String title : Arrays.copyOfRange(titles, 0, 5)) {
            int index = random.nextInt(authors.length);
            addBook(title, authors[index], Optional.empty());
        }

        for (String title : Arrays.copyOfRange(titles, 5, 8)) {
            int index = random.nextInt(authors.length);
            addMagazine(title, authors[index], "xx/xx/xxxx", Optional.empty());
        }

        System.out.println("Successfully Added Items to Library");
    }

    @Override
    public String toString() {
        StringBuilder description = new StringBuilder(100);

        String library_description = "Library: " + getId() + "\n";
        description.append("--------------------------------\n");
        description.append(library_description);

        if (!this.books.isEmpty()) {
            description.append("------------- Book -------------\n");
            for (Book book : this.books) {
                description.append(book.toString()).append("\n");
            }
        }

        if (!this.magazines.isEmpty()) {
            description.append("----------- Magazine -----------\n");
            for (Magazine magazine : this.magazines) {
                description.append(magazine.toString()).append("\n");
            }
        }

        return description.toString();
    }
}
