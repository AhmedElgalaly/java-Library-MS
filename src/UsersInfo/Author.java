package UsersInfo;

import BookConfigs.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Author extends User {
    private LocalDate dateOfDeath;
    private String biography;
    private String nationality;
    private Genre genre;
    private ArrayList<Book> books;

    // Default constructor
    public Author() {
        // Initialize with default values
    }

    // Parameterized constructor
    Author(String name, String surname, LocalDate dateOfBirth) {
        // Initialize with provided name, surname, and date of birth
        this.username = name + surname;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = null;
        this.biography = null;
        this.nationality = null;
        this.genre = null;
    }

    // Parameterized constructor with full details
    Author(String name, String surname, LocalDate dateOfBirth, ArrayList<Book> books,
           LocalDate dateOfDeath, String biography, String nationality, Genre genre) {
        // Utilize previous constructor to set common details
        this(name, surname, dateOfBirth);
        this.dateOfDeath = dateOfDeath;
        this.biography = biography;
        this.nationality = nationality;
        this.genre = genre;
        this.books = books;
    }

    // Getters and Setters
    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    // Method to add a book to the author's list of books
    public void addBook(Book book) {
        books.add(book);
    }

    // Method to remove a book from the author's list of books
    public void removeBook(Book book) {
        books.remove(book);
    }
}
