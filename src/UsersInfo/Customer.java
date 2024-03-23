package UsersInfo;

import BookConfigs.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Default.App;

public class Customer extends User {
    private ArrayList<Borrow> borrowedBooks;
    private static ArrayList<Borrow> borrowedBooksList = new ArrayList<>();
    private LocalDate registrationDate;
    private String customerMembership;
    private Double totalFine;
    private Customer inventory;

    // Getters and Setters
    public static ArrayList<Borrow> getBorrowedBooksList() {
        return borrowedBooksList;
    }

    public ArrayList<Borrow> getBorrowedBooks() {
        return borrowedBooks;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getCustomerMembership() {
        return customerMembership;
    }

    public Double getTotalFine() {
        return totalFine;
    }

    public void setBorrowedBooks(ArrayList<Borrow> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setCustomerMembership(String customerMembership) {
        this.customerMembership = customerMembership;
    }

    public void setTotalFine(Double totalFine) {
        this.totalFine = totalFine;
    }

    // Method to create a customer account
    public void createAccount(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.customerMembership = "Regular";
        this.borrowedBooks = new ArrayList<>();
        this.registrationDate = LocalDate.now();
        this.totalFine = 0.0;
    }

    // Method to manage customer account details
    public void manageAccount() {
        Scanner scn = new Scanner(System.in);
        mainLabel3:
        while (true) {
            System.out.println("1. Change Username");
            System.out.println("2. Change Password");
            System.out.println("3. Change Membership");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int choice = scn.nextInt();
            scn.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter new username: ");
                    username = scn.nextLine();
                    break;
                case 2:
                    System.out.println("Enter new password: ");
                    password = scn.nextLine();
                    break;
                case 3:
                    System.out.println("Enter new membership: ");
                    customerMembership = scn.nextLine();
                    break;
                case 4:
                    System.out.println("Exited Successfully!");
                    break mainLabel3;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    // Method to borrow a book
    public void borrowBook(Borrow book) {
        if (borrowedBooks.size() < 5) {
            if (book.getBook().getAvailability() == Status.AVAILABLE) {
                borrowedBooks.add(book);
                borrowedBooksList.add(book);
                book.getBook().setAvailability(Status.BORROWED);
            } else {
                System.out.println("Book is Unavailable");
            }
            return;
        }
        System.out.println("You have reached the maximum limit of borrowing books.");
    }

    // Method to return a book
    public void returnBook(Borrow book) {
        boolean found = false;
        for (Borrow b : borrowedBooks) {
            if (b.getBook().getISBN().equals(book.getBook().getISBN())) {
                book = b;
                found = true;
                break;
            }
        }

        if (found) {
            book.getBook().setAvailability(Status.AVAILABLE);
            totalFine += book.getLateFee();
            Inventory.setTotalFine(totalFine);
            borrowedBooks.remove(book);
            borrowedBooksList.remove(book);
        } else {
            System.out.println("Book not found in your borrowed list.");
        }
    }

    // Method to update borrowed book status
    public void updateBorrowedBook(Borrow book) {
        Scanner scn = new Scanner(System.in);
        System.out.println("1. Borrow");
        System.out.println("2. Return");
        System.out.println("Enter your choice: ");
        int choice = scn.nextInt();
        switch (choice) {
            case 1:
                borrowBook(book);
                break;
            case 2:
                returnBook(book);
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    // Method to view borrowed books
    public void viewBorrowedBooks() {
        System.out.println("Borrowed Books: ");
        try {
            for (Borrow book : borrowedBooks) {
                System.out.println(book.toString());
            }
        } catch (NullPointerException e) {
            System.out.println("There are no books");
        }
    }

    // Method to search for a book by title
    public ArrayList<Book> searchBookByTitle(String title) {
        return Inventory.searchBookByTitle(title);
    }

    // Method to search for a book by ISBN
    public Book searchBookByISBN(String ISBN) {
        return Inventory.searchBookByISBN(ISBN);
    }

    // Method for customer signup
    public static void CustomerSignUp() {
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter your username: ");
        String username = scn.next();

        System.out.println("Enter your Email: ");
        String email = scn.next();

        String password1, password2;
        // Loop to confirm password
        do {
            System.out.println("Enter your Password: ");
            password1 = scn.next();
            System.out.println("Confirm your Password: ");
            password2 = scn.next();
        } while (!password2.equals(password1));

        Customer account = new Customer();
        account.createAccount(username, email, password1);

        customerPage(account);
    }
    // Mathod for customer operations page
    public static void customerPage(Customer account) {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("1- Search for book by title");
            System.out.println("2- Search for book by ISBN");
            System.out.println("3- View borrowed books");
            System.out.println("4- Manage account");
            System.out.println("5- Borrow book");
            System.out.println("6- Update borrowed book");
            System.out.println("7- Cancel borrowed book");
            System.out.println("8- List all books");
            System.out.println("9- List all books of a specific genre");
            System.out.println("10- List number of book for a specific author");
            System.out.println("11- Total number of books");
            System.out.println("12- <-- Back");
            System.out.println("--------------------------------------------------------------");
            System.out.println("Enter your Choice : ");
            int choice;
            try {
                choice = App.scn.nextInt();
                System.out.println("--------------------------------------------------------------");
                App.scn.nextLine();
            } catch (InputMismatchException e) {
                choice = 10;
            }

            switch (choice) {

                // Search for book by title
                case 1:

                    System.out.println("Enter the title of the book: ");
                    String title = App.scn.nextLine();

                    try {

                        for (Book b : account.searchBookByTitle(title))
                            System.out.println(b.toString());

                    } catch (NullPointerException e) {
                        System.out.println("Error There Book is not in the library");
                    }

                    System.out.println("--------------------------------------------------------------");
                    break;

                // Search for book by ISBN
                case 2:
                    do {
                        System.out.println("Enter the ISBN of the book: ");
                        String ISBN = App.scn.next();

                        if (Book.verifyISBN(ISBN)) {

                            try {
                                System.out.println(account.searchBookByISBN(ISBN).toString());
                            } catch (NullPointerException e) {
                                System.out.println("Error There are no Books in the library: " + e.getMessage());
                            }

                            break;
                        } else
                            System.out.println("Wrong ISBN");

                    } while (true);
                    break;

                // View borrowed books
                case 3:
                    account.viewBorrowedBooks();
                    break;

                // Manage account
                case 4:
                    account.manageAccount();
                    break;

                // Borrow book
                case 5:

                    String ISBN1;
                    do {

                        System.out.println("Enter the ISBN of the book you want to borrow: ");
                        ISBN1 = App.scn.next();

                    } while (!Book.verifyISBN(ISBN1));

                    Book book;
                    try {

                        book = account.searchBookByISBN(ISBN1);

                    } catch (NullPointerException e) {

                        book = null;

                    }

                    if (book != null) {

                        Borrow borrow = new Borrow(book, LocalDate.now());
                        account.borrowBook(borrow);

                    } else {
                        System.out.println("Book not found.");
                    }

                    break;
                // Update borrowed book
                case 6:

                    System.out.println("Enter the ISBN of the book you want to update: ");
                    String ISBN2 = App.scn.next();

                    try {

                        Book book1 = account.searchBookByISBN(ISBN2);
                        if (book1 != null) {

                            Borrow borrow = new Borrow(book1, LocalDate.now());
                            account.updateBorrowedBook(borrow);

                        } else {
                            System.out.println("Book not found.");
                        }

                    } catch (NullPointerException e) {
                        System.out.println("Error There are no Books in the library: " + e.getMessage());
                    }
                    break;

                // Cancel borrowed book
                case 7:

                    System.out.println("Enter the ISBN of the book you want to cancel: ");
                    String ISBN3 = App.scn.next();
                    Book book2;

                    try {

                        book2 = account.searchBookByISBN(ISBN3);

                    } catch (NullPointerException e) {

                        book2 = null;

                    }
                    if (book2 != null) {

                        Borrow borrow = new Borrow(book2, LocalDate.now());
                        account.returnBook(borrow);

                    } else {
                        System.out.println("Book not found.");
                    }

                    break;

                case 8:
                    try {
                        Inventory.displayBooks();
                    }
                    catch (Exception e){
                        System.out.println("there are no books in the library");
                    }
                    break;
                case 9:
                    for(Genre i: Genre.values()) {
                        System.out.println(i.toString());
                        System.out.println("---------------------");
                        try {
                            for (Book j : Inventory.getBooks())
                                if (j.getGenreType().toString().equals(i.toString()))
                                    System.out.println(j.toString());
                        }catch (NullPointerException e){
                            System.out.println("there are no books in the library");
                        }
                    }
                    break;
                // Back to main menu
                case 10:
                    System.out.println("enter the name of the author you want to list his books");
                    String Name=App.scn.nextLine();
                    try {
                        for (Book i : Inventory.getBooks())
                            if (i.getAuthor().username.equals(Name))
                                System.out.println(i.toString());
                    }catch(NullPointerException e){
                        System.out.println("there are no books in the library");
                    }
                    break;

                case 11:
                    int count =0;
                    try{
                        for(Book i: Inventory.getBooks())
                            count++;
                    }finally {
                        System.out.println("there are " + count + " Books in the Library");
                    }
                case 12:
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}