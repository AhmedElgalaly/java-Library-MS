package Default;

import UsersInfo.Customer;
import UsersInfo.Librarian;


import java.util.InputMismatchException;
import java.util.Scanner;
public class App {

    public static Scanner scn = new Scanner(System.in);
    public static int actorPage(){
        System.out.println("---------Welcome to our Library Management System-----------");
        System.out.println("-----Visit our github page: https://github.com/AhmedElgalaly/java-Library-MS -----");
        System.out.println("1- Customer sign up");
        System.out.println("2- Librarian sign up");
        System.out.println("3- Exit");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Enter your Choice : ");
        try {
            int c = scn.nextInt();
            System.out.println("--------------------------------------------------------------");

            return c;
        }catch (InputMismatchException e){
            scn.nextLine();
            return 5;
        }
    }


    public static void main(String[] args){
        while(true) {
            switch (actorPage()) {
                case 1:
                    Customer.CustomerSignUp();
                    break;
                case 2:
                    Librarian.LibrarianSignUp();
                    break;
                case 3:
                    scn.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice! Please try again.");
                    break;
            }
        }
    }
}