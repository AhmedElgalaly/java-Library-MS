package UsersInfo;
import BookConfigs.Borrow;
import BookConfigs.Inventory;
import BookConfigs.Status;
import BookConfigs.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
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
        this.registrationDate = LocalDate.now();
        this.totalFine = 0.0;
        return true;
    }

    public void manageAccount(){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("1. Change Username");
            System.out.println("2. Change Password");
            System.out.println("3. Change Membership");
            System.out.println("4. Exit");
            System.out.println("Enter your choice: ");
            int choice = input.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter new username: ");
                    String newUsername = input.nextLine();
                    username = newUsername;
                    break;
                case 2:
                    System.out.println("Enter new password: ");
                    String newPassword = input.nextLine();
                    password = newPassword;
                    break;
                case 3:
                    System.out.println("Enter new membership: ");
                    String newMembership = input.nextLine();
                    customerMembership = newMembership;
                    break;
                case 4:
                    System.out.println("Exited Successfully!");
                    choice = 0;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }


    }
    public boolean borrowBook(Borrow book){
        if(borrowedBooks.size() < 5){
            borrowedBooks.add(book);
            borrowedBooksList.add(book);
            book.getBook().setAvailability(Status.BORROWED);
            return true;
        }
        System.out.println("You have reached the maximum limit of borrowing books.");
        return false;
    }

    public boolean returnBook(Borrow book){
        if(borrowedBooks.indexOf(book) != -1){
            book.getBook().setAvailability(Status.AVAILABLE);
            totalFine += book.getLateFee();
            Inventory.setTotalFine(totalFine);
            borrowedBooks.remove(book);
            borrowedBooksList.remove(book);
            return true;
        }
        System.out.println("Book not found in your borrowed list.");
        return false;
    }
    public void updateBorrowedBook(Borrow book){
        System.out.println("1. Borrow");
        System.out.println("2. Return");
        System.out.println("Enter your choice: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
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
        for(Borrow book: borrowedBooks){
            System.out.print(book.getBook().getTitle() + " ");
        }
    }
    public ArrayList<Book> searchBookByTitle(String title){

        return Inventory.searchBookByTitle(title);
    }
    public Book searchBookByISBN(String ISBN){
        return Inventory.searchBookByISBN(ISBN);
    }
    public static void CustomerSignUp(){
        Scanner scn = new Scanner(System.in);

        System.out.println("Enter your username: ");
        String username = scn.next();

        System.out.println("Enter your Email: ");
        String email = scn.next();

        String password1,password2;

        do {
            System.out.println("Enter your Password: ");
            password1 = scn.next();
            System.out.println("Confirm your Password: ");
            password2 = scn.next();
        }while(password2.equals(password1));

        Customer account = new Customer();
        account.createAccount(username,email,password1);

        scn.close();
        Customer.customerPage(account);
        return;
    }
    public static void customerPage(Customer account){
        Scanner scn = new Scanner(System.in);
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
            int choice = scn.nextInt();
            System.out.println("--------------------------------------------------------------");

            switch (choice) {
                case 1:
                    System.out.println("Enter the title of the book: ");
                    String title = scn.next();
                    for (Book b : account.searchBookByTitle(title))
                    {
                        System.out.println(b.toString());
                    }
                    System.out.println("--------------------------------------------------------------");
                    break;
                case 2:
                    System.out.println("Enter the ISBN of the book: ");
                    String ISBN = scn.next();
                    System.out.println(account.searchBookByISBN(ISBN).toString());
                    break;
                case 3:
                    account.viewBorrowedBooks();
                    break;
                case 4:
                    account.manageAccount();
                    break;
                case 5:
                    System.out.println("Enter the ISBN of the book you want to borrow: ");
                    String ISBN1 = scn.next();
                    Book book = account.searchBookByISBN(ISBN1);
                    if(book != null){
                        Borrow borrow = new Borrow(book, LocalDate.now());
                        account.borrowBook(borrow);
                    }else{
                        System.out.println("Book not found.");
                    }
                    break;
                case 6:
                    System.out.println("Enter the ISBN of the book you want to update: ");
                    String ISBN2 = scn.next();
                    Book book1 = account.searchBookByISBN(ISBN2);
                    if(book1 != null){
                        Borrow borrow = new Borrow(book1, LocalDate.now());
                        account.updateBorrowedBook(borrow);
                    }else{
                        System.out.println("Book not found.");
                    }
                    break;
                case 7:
                    System.out.println("Enter the ISBN of the book you want to cancel: ");
                    String ISBN3 = scn.next();
                    Book book2 = account.searchBookByISBN(ISBN3);
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
            scn.close();
        }
    }
}
