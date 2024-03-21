package Default;

import BookConfigs.Book;
import UsersInfo.Customer;
import UsersInfo.Librarian;
import UsersInfo.User;

import java.util.Scanner;
public class App {

    public static int actorPage(){
        Scanner scn = new Scanner(System.in);

        System.out.println("---------Welcome to our Library Management System-----------");
        System.out.println("-----Visit our github page: https://github.com/AhmedElgalaly/java-Library-MS -----");
        System.out.println("1- Customer sign up");
        System.out.println("2- Librarian sign up");
        System.out.println("3- Exit");
        System.out.println("--------------------------------------------------------------");
        System.out.println("Enter your Choice : ");
        int c = scn.nextInt();
        System.out.println("--------------------------------------------------------------");
        scn.close();

        return c;
    }


    public static void main(String[] args){
        while(true) {
            switch (actorPage()) {
                case 1:
                    Customer.CustomerSignUp();
                    break;
                case 2:
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid Choice! Please try again.");
                    break;
            }
        }
    }
}
