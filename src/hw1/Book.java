package hw1;

import java.util.ArrayList;

public class Book {
    public final String title;
    public final String author;
    final private ArrayList<Person> previousOwners = new ArrayList<>();
    private Person currentOwner;
    public final Integer id;

    public Book(String title, String author, Integer id) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.currentOwner = null;
    }

    public Book(String title, String author, Integer id, Person current_owner) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.currentOwner = current_owner;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Person getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(Person currentOwner) {
        this.currentOwner = currentOwner;
    }

    public void removeCurrentOwner() {
        this.previousOwners.add(currentOwner);
        this.currentOwner = null;
    }

// --Commented out by Inspection START (9/3/2024 5:58 PM):
//    public ArrayList<Person> getPreviousOwners() {
//        return previousOwners;
//    }
// --Commented out by Inspection STOP (9/3/2024 5:58 PM)

    @Override
    public String toString(){
        if (getCurrentOwner() == null) {
            return "Book ID: " + getId() +
                    "\nTitle: " + getTitle() +
                    "\nAuthor: " + getAuthor() +
                    "\nStatus: Available\n";
        }

        return "Book ID: " + getId() +
                "\nTitle: " + getTitle() +
                "\nAuthor: " + getAuthor() +
                "\nCurrent Owner: " + getCurrentOwner() + "\n";
    }

}
