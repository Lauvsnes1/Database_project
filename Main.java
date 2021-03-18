import java.util.*;
import java.sql.*;

public class Main {

    private static int userID;
    private static String type;
    private static String eMail;
    private static String userType;
    private static User user;

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

        if (("'" + resp.getEmail() + "'").equals(Email)) // De ekstra fnuttene er for å få riktig format på strengen
        {

            userID = resp.getUserID();
            Email = resp.getEmail();
            password = resp.getPassword();
            userType = resp.getUserType();
            System.out.println("Du er logget inn!\n Velkommen " + userType + " " + Email);

        } else {
            System.out.println("Feil passord eller brukerernavn");
        }

    }

    private static void Course() {
        // if (user == "student")
        {

        }

    }
}