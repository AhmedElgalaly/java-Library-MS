package UsersInfo;
import BookConfigs.Borrow;
import BookConfigs.Inventory;
import BookConfigs.Status;
import BookConfigs.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Default.App;

public class Customer extends User{
    private ArrayList<Borrow> borrowedBooks;
    private static ArrayList<Borrow> borrowedBooksList = new ArrayList<>();
    private LocalDate registrationDate;
    private String customerMembership;
    private Double totalFine;
    private Customer inventory;

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

    public boolean createAccount(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
        this.customerMembership = "Regular";
        this.borrowedBooks = new ArrayList<Borrow>();
        this.registrationDate = LocalDate.now();
        this.totalFine = 0.0;
        return true;
    }

    public void manageAccount(){
        mainLabel3:
        while(true){
            System.out.println("1. Change Username");
            System.out.println("2. Change Password");
            System.out.println("3. Change Membership");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int choice = App.scn.nextInt();
            App.scn.nextLine();
            switch(choice){
                case 1:
                    System.out.println("Enter new username: ");
                    new Scanner(System.in);
                    String newUsername = App.scn.nextLine();
                    username = newUsername;
                    break;
                case 2:
                    System.out.println("Enter new password: ");
                    new Scanner(System.in);
                    String newPassword = App.scn.nextLine();
                    password = newPassword;
                    break;
                case 3:
                    System.out.println("Enter new membership: ");
                    new Scanner(System.in);
                    String newMembership = App.scn.nextLine();
                    customerMembership = newMembership;
                    break;
                case 4:
                    System.out.println("Exited Successfully!");
                    choice = 0;
                    break mainLabel3;
                default:
                    System.out.println("Invalid choice");
            }
        }


    }
    public boolean borrowBook(Borrow book){
        if(borrowedBooks.size() < 5){
            if((book.getBook().getAvailability() == Status.AVAILABLE)) {
                borrowedBooks.add(book);
                borrowedBooksList.add(book);
                book.getBook().setAvailability(Status.BORROWED);
                return true;
            }
            else{
                System.out.println("Book is Unavailable");
                return false;
            }
        }
        System.out.println("You have reached the maximum limit of borrowing books.");
        return false;
    }

    public boolean returnBook(Borrow book){
        boolean found = false;

        for(Borrow i: borrowedBooks)
            if(i.getBook().equals(book.getBook()))
                found = true;

        if(found){
            book.getBook().setAvailability(Status.AVAILABLE);
            totalFine += book.getLateFee();
            Inventory.setTotalFine(totalFine);
            borrowedBooks.remove(book);
            borrowedBooksList.remove(book);
            return true;
        }else {
            System.out.println("Book not found in your borrowed list.");
            return false;
        }
    }
    public void updateBorrowedBook(Borrow book){
        System.out.println("1. Borrow");
        System.out.println("2. Return");
        System.out.println("Enter your choice: ");
        int choice = App.scn.nextInt();
        switch(choice){
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

    public void viewBorrowedBooks(){
        System.out.println("Borrowed Books: ");
        try {
            for (Borrow book : borrowedBooks) {
                //System.out.print(book.getBook().getTitle() + " ");
                System.out.println(book.toString());
            }
        }catch (NullPointerException e){
            System.out.println("There are no books");
        }
    }
    public ArrayList<Book> searchBookByTitle(String title){

        return Inventory.searchBookByTitle(title);
    }
    public Book searchBookByISBN(String ISBN){
        return Inventory.searchBookByISBN(ISBN);
    }
    public static void CustomerSignUp(){

        System.out.println("Enter your username: ");
        String username = App.scn.next();

        System.out.println("Enter your Email: ");
        String email = App.scn.next();

        String password1,password2;

        do {
            System.out.println("Enter your Password: ");
            password1 = App.scn.next();
            System.out.println("Confirm your Password: ");
            password2 = App.scn.next();
        }while(!password2.equals(password1));

        Customer account = new Customer();
        account.createAccount(username,email,password1);

        Customer.customerPage(account);
        return;
    }
    public static void customerPage(Customer account){
        while(true) {
            System.out.println("--------------------------------------------");
            System.out.println("1- Search for book by title");
            System.out.println("2- Search for book by ISBN");
            System.out.println("3- View borrowed books");
            System.out.println("4- Manage account");
            System.out.println("5- Borrow book");
            System.out.println("6- Update borrowed book");
            System.out.println("7- Cancel borrowed book");
            System.out.println("8- <-- Back");
            System.out.println("--------------------------------------------------------------");
            System.out.println("Enter your Choice : ");
            int choice;
            try {
                choice = App.scn.nextInt();
                System.out.println("--------------------------------------------------------------");
                App.scn.nextLine();
            }catch (InputMismatchException e){
                choice = 10;
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter the title of the book: ");
                    String title = App.scn.nextLine();
                    try {
                        for (Book b : account.searchBookByTitle(title))
                            System.out.println(b.toString());
                    }catch(NullPointerException e){
                        System.out.println("Error There are no Books in the library: " + e.getMessage());
                    }
                    System.out.println("--------------------------------------------------------------");
                    break;
                case 2:
                    do {
                        System.out.println("Enter the ISBN of the book: ");
                        String ISBN = App.scn.next();
                        if(Book.verifyISBN(ISBN)) {
                            try {
                                System.out.println(account.searchBookByISBN(ISBN).toString());
                            }catch(NullPointerException e){
                                System.out.println("Error There are no Books in the library: " + e.getMessage());
                            }

                            break;
                        }else
                            System.out.println("Wrong ISBN");
                    }while(true);
                    break;
                case 3:
                    account.viewBorrowedBooks();
                    break;
                case 4:
                    account.manageAccount();
                    break;
                case 5:
                    String ISBN1;
                    do {
                        System.out.println("Enter the ISBN of the book you want to borrow: ");
                        ISBN1 = App.scn.next();
                        if(Book.verifyISBN(ISBN1))
                            break;

                    }while(true);
                    Book book;
                    try {
                        book = account.searchBookByISBN(ISBN1);
                    }catch (NullPointerException e){
                       book = null;
                    }

                    if(book != null){
                        Borrow borrow = new Borrow(book, LocalDate.now());
                        account.borrowBook(borrow);
                    }else{
                        System.out.println("Book not found.");
                    }

                    break;
                case 6:
                    System.out.println("Enter the ISBN of the book you want to update: ");
                    String ISBN2 = App.scn.next();
                    try {
                    Book book1 = account.searchBookByISBN(ISBN2);
                    if(book1 != null){
                        Borrow borrow = new Borrow(book1, LocalDate.now());
                        account.updateBorrowedBook(borrow);
                    }else{
                        System.out.println("Book not found.");
                    }
                    }catch (NullPointerException e){
                        System.out.println("Error There are no Books in the library: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("Enter the ISBN of the book you want to cancel: ");
                    String ISBN3 = App.scn.next();
                    Book book2;
                    try {
                        book2 = account.searchBookByISBN(ISBN3);
                    }catch (NullPointerException e){
                        book2 =null;
                    }
                    if(book2 != null) {
                        Borrow borrow = new Borrow(book2, LocalDate.now());
                        account.returnBook(borrow);
                    }else {
                        System.out.println("Book not found.");
                    }

                    break;
                case 8:
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}