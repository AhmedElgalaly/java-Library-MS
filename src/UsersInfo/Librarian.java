package UsersInfo;
import BookConfigs.*;
import UsersInfo.*;

import java.lang.reflect.GenericArrayType;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Librarian extends User{
    private static ArrayList<Inventory> inventory = new ArrayList<>();
    private Double Salary;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate hireDate;


    Librarian(String username, String email, String password, String phone
            ,String address, LocalDate dateOfBirth, char gender, Double salary,
              LocalTime startDate, LocalTime endDate, LocalDate hireDate)
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;


        Salary = salary;
        this.startTime = startDate;
        this.endTime = endDate;
        this.hireDate = hireDate;
    }
    public static ArrayList<Inventory> getInventory() {
        return inventory;
    }
    public Inventory getInventory(int InventoryID)
    {
        for (Inventory i : inventory)
        {
            if (i.getInventoryID() == InventoryID)
                return i;
        }
        return null;
    }
    public void addInventory(Inventory inventory)
    {
        this.inventory.add(inventory);
    }
    public static void removeInventory(int InventoryID)
    {
        for (Inventory i : inventory)
        {
            if (i.getInventoryID() == InventoryID)
            {
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
    public void setWorkingHour(LocalTime startTime, LocalTime endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    // TODO: DOne 😀
    public static void generateReport()
    {
        System.out.println("The total fare of the books : " + Inventory.getTotalFine());
        System.out.println("The total number of books borrowed : " + Customer.getBorrowedBooksList().size());

        Double totalFine = 0.0;
        for(Borrow borrow : Customer.getBorrowedBooksList()) {
            totalFine += borrow.getLateFee();
        }
        System.out.println("The total number of pending fare : " + totalFine);
    }
    public static void LibrarianPage(){
        Scanner scn = new Scanner(System.in);
        System.out.println("--------------------------------------------");
        System.out.println("1- Add inventory");
        System.out.println("2- Remove inventory");
        System.out.println("3- Generate report");
        System.out.println("4- Add Book");
        System.out.println("5- Remove Book");
        System.out.println("6- <--Back");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Enter your Choice : ");
        int choice = scn.nextInt();
        System.out.println("---------------------------------------------------");
        switch (choice) {
            case 1:
                System.out.println("enter the invintory ID");
                int ID= scn.nextInt();
                System.out.println("enter the invintory capacity");
                int capacity=scn.nextInt();
                System.out.println("enter the invintory location");
                new Scanner(System.in);
                String location = scn.nextLine();
                Inventory newinventory= new Inventory(ID,capacity,location);
                inventory.add(newinventory);
                System.out.println("inventory successfuly added");
                break;
            case 2:
                System.out.println("enter the invintory ID");
                ID= scn.nextInt();
                System.out.println("enter the invintory capacity");
                capacity=scn.nextInt();
                System.out.println("enter the invintory location");
                new Scanner(System.in);
                location = scn.nextLine();
                Inventory removeinventory= new Inventory(ID,capacity,location);
                inventory.remove(removeinventory);
                break;
            case 3:
                generateReport();
                break;
            case 4:

                // TODO : Galaly do this ana hftar
                System.out.println("Enter book title: ");
                String title = scn.nextLine();
                new Scanner(System.in);
                System.out.println("Enter author name: ");
                String name = scn.nextLine();
                System.out.println("Enter author surname: ");
                String surname = scn.nextLine();
                System.out.println("Enter author date of birth:day/month/year ");
                int day = scn.nextInt();
                int month = scn.nextInt();
                int year = scn.nextInt();
                while( day>31 || month>12 ||year<0||day<0||month<0) {
                    System.out.println("invalid date try again:");
                     day = scn.nextInt();
                     month = scn.nextInt();
                     year = scn.nextInt();
                }
                LocalDate DOB = LocalDate.of(year,month,day);

                Author bookauthor= new Author(name,surname,DOB);
                new Scanner(System.in);

                String ISBN;
                do {
                    System.out.println("Enter ISBN: ");
                    ISBN = scn.next();
                    if(Book.verifyISBN(ISBN))
                        break;
                    else
                        System.out.println("Wrong ISBN");
                }while(true);

                String genreType;
                Genre newbookgenre;

                mainLabel:
                do {
                    System.out.println("Enter Genre Type: ");
                     genreType = scn.next();

                     for(Genre i: Genre.values())
                         if(i.toString().equals(genreType)) {
                             newbookgenre=Genre.valueOf(genreType);
                             break mainLabel;
                         }
                        else
                             System.out.println("Unrecodnizable Genere");
                }while(true);


                String availability;
                Status newbookstatus;

                mainLabel2:
                do {
                    System.out.println("Enter availbaility of The Book: ");
                    availability = scn.next();

                    for(Status i: Status.values())
                        if(i.toString().equals(availability)) {
                            newbookstatus=Status.valueOf(availability);
                            break mainLabel2;
                        }
                        else
                            System.out.println("Unrecodnizable Status");
                }while(true);

                Book newbook= new Book(title,ISBN,bookauthor,newbookgenre,newbookstatus);

                System.out.println("Choose inventory to the book to : ");
                for (Inventory i : inventory)
                {
                    System.out.println(i.getInventoryID() + " : " + i.getLocation());
                }
                int inventoryID = scn.nextInt();
                new Scanner(System.in);
                Inventory inv_toAdd = Inventory.getInventoryByID(inventoryID);
                inv_toAdd.addBook(newbook);
                break;
            case 5:
                System.out.println("Choose inventory to remove book from : ");
                for (Inventory i : inventory)
                {
                    System.out.println(i.getInventoryID() + " : " + i.getLocation());
                }
                inventoryID = scn.nextInt();
                new Scanner(System.in);
                Inventory inv_toRemove = Inventory.getInventoryByID(inventoryID);
                System.out.println("Enter Book ISBN to remove : ");
                ISBN = scn.nextLine();
                inv_toRemove.removeBook(Inventory.searchBookByISBN(ISBN));
                try {
                    Book.deleteBook(Inventory.searchBookByISBN(ISBN));
                }catch (Throwable e) {
                    throw new RuntimeException(e);
                }
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid Choice");
        }
        scn.close();
    }
}