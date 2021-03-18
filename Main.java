import java.util.*;
import java.sql.*;

public class Main {

    private static String userID;
    private static String type;
    private static String eMail;
    private static User UseCase1Ctrl;

    public static Scanner scannerObject = new Scanner(System.in);

    public static void main(String[] args) {
        LogInn();

    }

    private static void LogInn() {

        System.out.println("\nEmail: ");
        String Email = "\'" + scannerObject.next() + "\'";

        System.out.println("\nPassword: ");
        String password = "\'" + scannerObject.next() + "\'";

        UseCase1Ctrl useCase1Ctrl = new UseCase1Ctrl();
        useCase1Ctrl.connect();
        User resp = useCase1Ctrl.authUser(Email, password);

        System.out.println(Email);
        System.out.println(password);
        if (resp.getEmail() == Email) {
            System.out.println("Logget inn!");

        }

    }

}