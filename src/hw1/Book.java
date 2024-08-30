package hw1;

public class Book {
    public final String title;
    public final String author;
    private Person current_owner;
    public final Integer id;

    public Book(String title, String author, Integer id, Person current_owner) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.current_owner = current_owner;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Person getCurrent_owner() {
        return current_owner;
    }

    public void setCurrent_owner(Person current_owner) {
        this.current_owner = current_owner;
    }

    @Override
    public String toString(){
        return "Book Title: " + getTitle() + "\n" +
                "Author: " + getAuthor() + "\n" +
                "Current Owner: " + getCurrent_owner() + "\n";
    }

}
