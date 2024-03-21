package UsersInfo;
import BookConfigs.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Author extends User{
    private LocalDate dateOfDeath;
    private String biography;
    private String nationality;
    private Genre genre;
    private ArrayList<Book> books;
    public Author(){

    }

    Author(String name, String surname, LocalDate dateOfBirth) {
        this.username = name + surname;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = null;
        this.biography = null;
        this.nationality = null;
        this.genre = null;

    }
    Author(String name, String surname, LocalDate dateOfBirth,ArrayList<Book> books
            , LocalDate dateOfDeath, String biography, String nationality, Genre genre) {
        this(name, surname, dateOfBirth);
        this.dateOfDeath = dateOfDeath;
        this.biography = biography;
        this.nationality = nationality;
        this.genre = genre;
        this.books=books;
    }
    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }
    public String getBiography() {
        return biography;
    }
    public String getNationality() {
        return nationality;
    }
    public Genre getGenre() {
        return genre;
    }
    public ArrayList<Book> getBooks() {
        return books;
    }
    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }
    public void addBook(Book book) {
        books.add(book);
    }
    public void removeBook(Book book) {
        books.remove(book);
    }
}
