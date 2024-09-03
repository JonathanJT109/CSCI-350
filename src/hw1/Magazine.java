package hw1;

// Is a magazine a book?
public class Magazine extends Book{
    public final String publicationDate;

    public Magazine(String title, String author, Integer id, String publicationDate, Person person) {
        super(title, author, id, person);
        this.publicationDate = publicationDate;
    }

    public Magazine(String title, String author, Integer id, String publication_date) {
        super(title, author, id);
        this.publicationDate = publication_date;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    @Override
    public String toString(){
        if (getCurrentOwner() == null) {
            return "Magazine ID: " + getId() +
                    "\nTitle: " + getTitle() +
                    "\nAuthor: " + getAuthor() +
                    "\nPublication Date: " + getPublicationDate() +
                    "\nStatus: Available\n";
        }
        return "Magazine ID: " + getId() +
                "\nTitle: " + getTitle() +
                "\nAuthor: " + getAuthor() +
                "\nPublication Date: " + getPublicationDate() +
                "\nCurrent Owner: " + getCurrentOwner() + "\n";
    }

}
