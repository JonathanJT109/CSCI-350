package hw1;

public class Library {
    private static Integer nextId = 1;
    private final Integer id;
    private Integer book_id = 1;
    private Integer magazine_id = 1;
    public String name;

    public Library() {
        this.id = nextId++;
    }

    public Integer getId() {
        return id;
    }

    public void addBook() {

    }
}
