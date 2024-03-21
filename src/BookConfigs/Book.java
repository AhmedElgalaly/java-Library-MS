package BookConfigs;

import UsersInfo.Author;
import java.util.Objects;

public class Book {

    // ATTRIBUTES
    private String title;
    private Author author;
    private String ISBN;
    private Genre genreType;
    private Status availability;

    private int copy;


    // METHODS

    Book(){
        this.title = "";
        this.author = new Author();
        this.ISBN = "";
        this.genreType = Genre.NONE;
        this.availability = Status.UNAVAILABLE;
        this.copy = 0;
    }
   public Book(String title, String ISBN,Author author, Genre genreType, Status availability){
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.genreType = genreType;
        this.availability = availability;
    }
    Book(String title,String ISBN, Author author, Genre genreType, Status availability, int copy){
        this(title, ISBN, author, genreType, availability);
        this.copy = copy;
    }
    public String getTitle(){
        return title;
    }

    public Author getAuthor(){
        return author;
    }

    public String getISBN(){
        return ISBN;
    }

    public Genre getGenreType() {
        return genreType;
    }

    public Status getAvailability() {
        return availability;
    }

    public int getCopies() {
        return copy;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setGenreType(Genre genreType) {
        this.genreType = genreType;
    }

    public void setAvailability(Status availability) {
        this.availability = availability;
    }

    public void setCopies(int copy) {
        // add copy
        this.copy += copy;
    }


    // TODO: Check if the ISBN-10 is valid or not ✅
    // TODO: Check if the ISBN-13 is valid or not ✅

    // TODO: Explain your code to the idiots
    public static boolean verifyISBN(String ISBN) {
        int ISBNtype = 0;
        ISBN = ISBN.replaceAll("-","");
        for (int i = 0; i < ISBN.length(); i++)
            if (Character.isDigit(ISBN.charAt(i)))
                ISBNtype++;

        if (ISBNtype == 10) {
            int sum = 0;
            for (int i = 0; i < ISBN.length(); i++)
                if (Character.isDigit(ISBN.charAt(i)))
                    sum += (Integer.parseInt((ISBN.charAt(i) + "")) * (10 - i));

            return sum % 11 == 0;


        } else if (ISBNtype == 13) {
            int sum = 0;
            for (int i = 0; i < ISBN.length(); i++)
                if (Character.isDigit(ISBN.charAt(i)))
                    sum += (Integer.parseInt( ISBN.charAt(i) + "") * ((i % 2 == 0) ? 1 : 3));

            return sum % 10 == 0;
        } else
            return false;
    }

    public static void deleteBook(Book book) throws Throwable {
        book.finalize();
    }


    // TODO: override equals method ✅
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return this.getISBN().equals( ((Book) o).getISBN() );
    }




    // TODO: override toString method ✅
    @Override
    public String toString() {
        return String.format("Title: %s\n" +
                    "Author: %s\n" +
                    "ISBN: %s\n" +
                    "Genre: %s\n" +
                    "Availability: %s\n" +
                    "Copies: %d",
                    this.getTitle(),
                    this.getAuthor(),
                    this.getISBN(),
                    this.getGenreType(),
                    this.getAvailability(),
                    this.getCopies());

    }
}