package hw1;

import java.util.ArrayList;

public class Book {
    public final String title;
    public final String author;
    private ArrayList<Person> previousOwners;
    private Person currentOwner;
    public final Integer id;

    public Book(String title, String author, Integer id) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.currentOwner = null;
        previousOwners = new ArrayList<>();
    }

    public Book(String title, String author, Integer id, Person current_owner) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.currentOwner = current_owner;
        previousOwners = new ArrayList<>();
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

    public ArrayList<Person> getPreviousOwners() {
        return previousOwners;
    }

    @Override
    public String toString(){
        if (getCurrentOwner() == null) {
            return "Book: " + getId() +
                    "\nTitle: " + getTitle() +
                    "\nAuthor: " + getAuthor() +
                    "\nStatus: Available\n";
        }

        return "Book: " + getId() +
                "\nTitle: " + getTitle() +
                "\nAuthor: " + getAuthor() +
                "\nCurrent Owner: " + getCurrentOwner() + "\n";
    }

}
