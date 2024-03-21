package BookConfigs;
import BookConfigs.*;
import UsersInfo.Librarian;

import java.util.ArrayList;

public class Inventory {
    // ATTRIBUTES
    private int inventoryID;
    private static ArrayList<Book> books;
    private ArrayList<Book> booksInInventory;
    private int capacity;
    private String location;

    private static Double totalFine;

    // METHODS

    // CONSTRUCTORS
    public Inventory(int inventoryID, int capacity, String location) {
        this.inventoryID = inventoryID;
        this.capacity = capacity;
        this.location = location;
        books = new ArrayList<Book>();
        booksInInventory = new ArrayList<Book>();
        totalFine = 0.0;
    }
    public static ArrayList<Book> getBooks() {
        return books;
    }
    public ArrayList<Book> getBooksInInventory() {
        return booksInInventory;
    }
    public int getInventoryID() {
        return inventoryID;
    }
    public void setInventoryID(int inventoryID) {
        this.inventoryID = inventoryID;
    }
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static Double getTotalFine() {
        return totalFine;
    }
    public static void setTotalFine(Double fine) {
        totalFine += fine;
    }
    public void addBook(Book book){
        books.add(book);
        booksInInventory.add(book);
    }

    public void removeBook(Book book){
        books.remove(book);
        booksInInventory.remove(book);
    }


    public static ArrayList<Book> searchBookByTitle(String title){
        ArrayList<Book> b = new ArrayList<Book>();
        for(Book i: books)
            if(i.getTitle().equals(title))
                b.add(i);
        return b;
    }
     public ArrayList<Book> searchBookInInventory(String title){
        ArrayList<Book> b = new ArrayList<Book>();
        for(Book i: booksInInventory)
            if(i.getTitle().equals(title))
                b.add(i);
        return b;
    }

    public static Book searchBookByISBN(String ISBN){

        for(Book i: books)
            if(i.getISBN().equals(ISBN))
                return i;
        return null;
    }

    public Book searchBookInInventoryByISBN(String ISBN){
        for(Book i: booksInInventory)
            if(i.getISBN().equals(ISBN))
                return i;
        return null;
    }


    public static void displayBooks(){
        for(Book i: books)
            System.out.println(i.toString());
    }

    public void displayBooksInInventory(){
        for(Book i: booksInInventory)
            System.out.println(i.toString());
    }
    public static void viewInventories(){

    }
    public static Inventory getInventoryByID(int inventoryID){
        for(Inventory i: Librarian.getInventory())
            if(i.getInventoryID() == inventoryID)
                return i;
        return null;
    }
}
