import java.util.*;
import java.sql.*;

public class Main {

    private static String userID;
    private static String type;
    private static String eMail;

    public static Scanner scannerObject = new Scanner(System.in);

    public static void main(String[] args) {
        LogInn();

    }

    private static void LogInn() {
        UseCase1Ctrl useCase1Ctrl = new UseCase1Ctrl();
        useCase1Ctrl.connect();

    }

}