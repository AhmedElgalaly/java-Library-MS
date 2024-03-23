package UsersInfo;

import BookConfigs.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;

import Default.App;

public class Librarian extends User {
    private static final ArrayList<Inventory> inventory = new ArrayList<>();
    private Double Salary;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate hireDate;

    Librarian(String username, String email, String password, String phone,
              String address, LocalDate dateOfBirth, char gender, Double salary,
              LocalTime startDate, LocalTime endDate, LocalDate hireDate) {

        super(username, email, password, phone, address, dateOfBirth, gender);

        Salary = salary;
        this.startTime = startDate;
        this.endTime = endDate;
        this.hireDate = hireDate;
    }

    public Librarian() {
        this.Salary = 0.0;
        this.startTime = null;
        this.endTime = null;
        this.hireDate = null;
    }

    public boolean createAccount(String username, String email, String password, char gender) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.address = null;
        return true;
    }

    public static ArrayList<Inventory> getInventory() {
        return inventory;
    }

    public Inventory getInventory(int InventoryID) {

        // This method returns the inventory object with the given InventoryID
        for (Inventory i : inventory) {
            if (i.getInventoryID() == InventoryID)
                return i;
        }

        return null;
    }

    // This method adds an inventory object to the inventory list
    public void addInventory(Inventory inventory) {

        Librarian.inventory.add(inventory);

    }

    // This method removes an inventory object from the inventory list using the Inventory ID
    public static void removeInventory(int InventoryID) {

        for (Inventory i : inventory) {

            if (i.getInventoryID() == InventoryID) {

                inventory.remove(i);
                break;

            }

        }

    }

    public Double getSalary() {
        return Salary;
    }

    public LocalTime getStartDate() {
        return startTime;
    }

    public LocalTime getEndDate() {
        return endTime;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setSalary(Double salary) {
        Salary = salary;
    }

    public void setStartDate(LocalTime startDate) {
        this.startTime = startDate;
    }

    public void setEndDate(LocalTime endDate) {
        this.endTime = endDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public void setWorkingHour(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }


    /*
     * This method generates a report for the library, displaying the total number of books in the library,
     * the total fare of the books, the total number of books borrowed, and the total number of pending fare.
     */
    public static void generateReport() {

        System.out.println("The total number of books in the library : " + Inventory.getBooks().size());
        System.out.println("The total fare of the books : " + Inventory.getTotalFine());
        System.out.println("The total number of books borrowed : " + Customer.getBorrowedBooksList().size());

        Double totalFine = 0.0;
        // Calculate the total fine of the borrowed books
        for (Borrow borrow : Customer.getBorrowedBooksList()) {
            totalFine += borrow.getLateFee();
        }

        System.out.println("The total number of pending fare : " + totalFine);
    }

    // This method is used to sign up a librarian to the library system
    public static void LibrarianSignUp() {

        System.out.println("Enter your username: ");
        String username = App.scn.next();

        System.out.println("Enter your Email: ");
        String email = App.scn.next();

        String password1, password2;

        do {
            System.out.println("Enter your Password: ");
            password1 = App.scn.next();
            System.out.println("Confirm your Password: ");
            password2 = App.scn.next();
        } while (!password2.equals(password1));


        System.out.println("Enter Gender: ");
        char gender = App.scn.next().charAt(0);

        Librarian account = new Librarian();
        account.createAccount(username, email, password1, Character.toUpperCase(gender));

        Librarian.LibrarianPage();
    }

    /*
     * This method represents the Librarian page, where a librarian can perform various tasks such as
     * adding or removing inventory, generating reports, adding books, and removing books.
     */
    public static void LibrarianPage() {
        while (true) {
            System.out.println("--------------------------------------------");
            System.out.println("1- Add inventory");
            System.out.println("2- Remove inventory");
            System.out.println("3- Generate report");
            System.out.println("4- Add Book");
            System.out.println("5- Remove Book");
            System.out.println("6- <--Back");
            System.out.println("--------------------------------------------------------------");
            System.out.println("Enter your Choice : ");
            int choice = App.scn.nextInt();
            System.out.println("---------------------------------------------------");
            App.scn.nextLine();


            switch (choice) {

                // This case adds an inventory to the library
                case 1:
                    System.out.println("enter the inventory ID");
                    int ID = App.scn.nextInt();

                    System.out.println("enter the inventory capacity");
                    int capacity = App.scn.nextInt();

                    // TODO: don't remove this again ya belal it won't read location if you do
                    App.scn.nextLine();

                    System.out.println("enter the inventory location");
                    String location = App.scn.nextLine();

                    Inventory newinventory = new Inventory(ID, capacity, location);
                    inventory.add(newinventory);

                    System.out.println("inventory successfully added");
                    break;

                // This case removes an inventory from the library
                case 2:
                    System.out.println("enter the inventory ID");
                    ID = App.scn.nextInt();

                    System.out.println("enter the inventory capacity");
                    capacity = App.scn.nextInt();

                    System.out.println("enter the inventory location");
                    App.scn.nextLine();

                    location = App.scn.nextLine();
                    Inventory removeinventory = new Inventory(ID, capacity, location);
                    inventory.remove(removeinventory);
                    break;

                // This case generates a report for the library
                case 3:
                    generateReport();
                    break;

                /* This case adds a book to the library. The user is prompted to enter the book title, author name, author surname,
                 * author date of birth, ISBN, genre type, availability, and the inventory ID to add the book to.
                 */
                case 4:
                    if (inventory.size() == 0) {
                        System.out.println("Please add an inventory first!");
                        break;
                    }
                    // TODO : Galaly do this ana hftar
                    System.out.println("Enter book title: ");
                    String title = App.scn.nextLine();

                    System.out.println("Enter author first name: ");
                    String name = App.scn.nextLine();

                    System.out.println("Enter author surname: ");
                    String surname = App.scn.nextLine();

                    System.out.println("Enter author date of birth:day/month/year ");
                    int day = App.scn.nextInt();
                    int month = App.scn.nextInt();
                    int year = App.scn.nextInt();
                    while (day > 31 || month > 12 || year < 0 || day < 0 || month < 0) {
                        System.out.println("invalid date try again:");
                        day = App.scn.nextInt();
                        month = App.scn.nextInt();
                        year = App.scn.nextInt();
                    }

                    LocalDate DOB = LocalDate.of(year, month, day);

                    Author bookauthor = new Author(name, surname, DOB);
                    App.scn.nextLine();

                    String ISBN;
                    do {
                        System.out.println("Enter ISBN: ");
                        ISBN = App.scn.next();
                        if (Book.verifyISBN(ISBN))
                            break;
                        else
                            System.out.println("Wrong ISBN");
                    } while (true);

                    String genreType;
                    Genre newbookgenre;


                    mainLabel:
                    do {
                        System.out.println("Enter Genre Type: ");
                        genreType = App.scn.next();


                        for (Genre i : Genre.values()) {
                            if (i.toString().equals(genreType.toUpperCase()))
                                break mainLabel;
                        }

                    } while (true);

                    newbookgenre = Genre.valueOf(genreType.toUpperCase());

                    String availability;
                    Status newbookstatus;

                    mainLabel2:
                    do {
                        System.out.println("Enter availability of The Book: ");
                        availability = App.scn.next();

                        for (Status i : Status.values())
                            if (i.toString().equals(availability.toUpperCase()))
                                break mainLabel2;
                    } while (true);

                    newbookstatus = Status.valueOf(availability.toUpperCase());

                    Book newbook = new Book(title, ISBN, bookauthor, newbookgenre, newbookstatus);

                    System.out.println("Choose inventory to the book to : ");
                    for (Inventory i : inventory) {
                        System.out.println(i.getInventoryID() + " : " + i.getLocation());
                    }

                    int inventoryID = App.scn.nextInt();
                    App.scn.nextLine();

                    Inventory inv_toAdd = Inventory.getInventoryByID(inventoryID);
                    if (inv_toAdd == null) {
                        System.out.println("Please enter a valid inventory ID!");
                        break;
                    }


                    inv_toAdd.addBook(newbook);
                    break;

                // This case removes a book from the library
                case 5:
                    System.out.println("Choose inventory to remove book from : ");
                    for (Inventory i : inventory) {
                        System.out.println(i.getInventoryID() + " : " + i.getLocation());
                    }
                    inventoryID = App.scn.nextInt();
                    App.scn.nextLine();
                    Inventory inv_toRemove = Inventory.getInventoryByID(inventoryID);
                    System.out.println("Enter Book ISBN to remove : ");
                    ISBN = App.scn.nextLine();
                    assert inv_toRemove != null;
                    inv_toRemove.removeBook(Inventory.searchBookByISBN(ISBN));
                    try {
                        Book.deleteBook(Inventory.searchBookByISBN(ISBN));
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                    break;

                // This case returns the user to the previous page
                case 6:
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}