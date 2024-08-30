package hw1;

// Is a magazine a book?
public class Magazine extends Book{
    public final String publication_date;

    public Magazine(String title, String author, Integer id, String publication_date, Person person) {
        super(title, author, id, person);
        this.publication_date = publication_date;
    }

    public String getPublication_date() {
        return publication_date;
    }

    @Override
    public String toString(){
        return "Book Title: " + getTitle() + "\n" +
                "Author: " + getAuthor() + "\n" +
                "Publication Date: " + getPublication_date() + "\n" +
                "Current Owner: " + getCurrent_owner() + "\n";
    }

}
