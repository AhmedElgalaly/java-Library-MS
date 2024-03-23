package BookConfigs;

import UsersInfo.Librarian;
import java.util.ArrayList;

public class Inventory {
    // ATTRIBUTES
    private int inventoryID;
    private static ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Book> booksInInventory;
    private int capacity;
    private String location;
    private static Double totalFine;

    // CONSTRUCTORS
    public Inventory(int inventoryID, int capacity, String location) {
        this.inventoryID = inventoryID;
        this.capacity = capacity;
        this.location = location;
        booksInInventory = new ArrayList<>();
        totalFine = 0.0;
    }

    // Getters and Setters
    public static ArrayList<Book> getBooks() { return books; }
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

    // Method to add a book to the inventory
    public void addBook(Book book) {

        books.add(book); // Add the book to the entire inventory

        booksInInventory.add(book); // Add the book to the current inventory

    }

    // Method to remove a book from the inventory
    public void removeBook(Book book) {

        books.remove(book); // Remove the book from the entire inventory

        booksInInventory.remove(book); // Remove the book from the current inventory

    }

    // Method to search for books by title in the entire inventory
    public static ArrayList<Book> searchBookByTitle(String title) {

        ArrayList<Book> foundBooks = new ArrayList<>();

        // Loop through the entire inventory to find the books matching the title
        for (Book book : books)
            if (book.getTitle().equals(title))
                foundBooks.add(book);

        return foundBooks;
    }

    // Method to search for books by title in the current inventory
    public ArrayList<Book> searchBookInInventory(String title) {

        ArrayList<Book> foundBooks = new ArrayList<>();

        // Loop through the current inventory to find the books matching the title
        for (Book book : booksInInventory)
            if (book.getTitle().equals(title))
                foundBooks.add(book);

        return foundBooks;
    }

    // Method to search for a book by ISBN in the entire inventory
    public static Book searchBookByISBN(String ISBN) {

        // Loop through the entire inventory to find the book matching the ISBN
        for (Book book : books)
            if (book.getISBN().equals(ISBN))
                return book;

        return null;
    }

    // Method to search for a book by ISBN in the current inventory
    public Book searchBookInInventoryByISBN(String ISBN) {

        // Loop through the current inventory to find the book matching the ISBN
        for (Book book : booksInInventory)
            if (book.getISBN().equals(ISBN))
                return book;

        return null;
    }

    // Method to display all books in the entire inventory
    public static void displayBooks() {

        for (Book book : books)
            System.out.println(book.toString());

    }

    // Method to display all books in the current inventory
    public void displayBooksInInventory() {

        for (Book book : booksInInventory)
            System.out.println(book.toString());
            // Call the toString method of the Book class to display the book

    }

    // Method to view all inventories
    public static void viewInventories() {

        // Loop through the inventories to display them using the toString method
        for (Inventory inventory : Librarian.getInventory())
            System.out.println(inventory.toString());

    }

    // Method to get inventory by ID
    public static Inventory getInventoryByID(int inventoryID) {

        // Loop through the inventories to find the inventory matching the ID
        for (Inventory inventory : Librarian.getInventory())
            if (inventory.getInventoryID() == inventoryID)
                return inventory;

        return null;
    }
}
