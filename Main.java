import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static int userID;
    private static int courseID;
    private static String type;
    private static String eMail;
    private static String userType;
    private static User user;

    public static Scanner scannerObject = new Scanner(System.in);

    public static void main(String[] args) {
        logInn();

        chooseCourse(userID);

        System.out.println("\nSkriv inn inn ID på kurset du ønsker å gå inn på: ");
        int courseID = scannerObject.nextInt();
        chooseFolder(courseID);

        System.out.println("Trykk 1 fpor å søke etter tråd eller trykk 0 for å velge mappe");
        int choice = scannerObject.nextInt();
        if (choice == 1) {
            scannerObject.nextLine();
            System.out.println("Søkeord: ");
            String keyword = scannerObject.nextLine();
            searchFor(keyword, courseID);

        }

        System.out.println("\nSkriv inn inn ID på mappen du ønsker å gå inn i: ");
        int folderID = scannerObject.nextInt();

        System.out.println("\nTrykk 0 for å opprette tråd og 1 for å opprette svar.");
        int ans = scannerObject.nextInt();
        if (ans == 0) {
            createPost(folderID);
        } else {
            replyTo(folderID);
        }
    }

    private static void logInn() {

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
            System.out.println("Du er logget inn!\nVelkommen, " + userType + " " + Email);

        } else {
            System.out.println("Feil passord eller brukerernavn");
        }

    }

    private static void chooseCourse(int userID) {
        System.out.println("Her er alle kursene du er medlemm i: \n");// Display all courses:
        InfoCtrl courseCtrl = new InfoCtrl();
        courseCtrl.connect();
        ArrayList<Course> courses = courseCtrl.getCourses(userID);
        for (Course course : courses) {
            System.out.println(course.getCourseID() + "    " + course.getCourseName());

        }
    }

    private static void chooseFolder(int courseID) {
        System.out.println("Her er alle mappene som finnes: ");
        InfoCtrl folderCtrl = new InfoCtrl();
        folderCtrl.connect();
        ArrayList<Folder> folders = folderCtrl.getFolder(courseID);
        for (Folder folder : folders) {
            System.out.println(folder.getFolderID() + "    " + folder.getFolderName());

        }
    }

    private static void createPost(int folderID) {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100000 + 1);

        UseCase2Ctrl useCase2Ctrl = new UseCase2Ctrl();
        useCase2Ctrl.connect();
        scannerObject.nextLine();
        System.out.println("Skriv inn tittel på spørsmålet ditt: ");
        String header = scannerObject.nextLine();
        System.out.println("Skriv inn spørsmålet ditt: ");
        String content = scannerObject.nextLine();
        try {
            useCase2Ctrl.startThread();
            useCase2Ctrl.makeThread(randomNum, 1, content, "question", header, userID, folderID);

        } catch (Exception e) {

        }

    }

    private static void replyTo(int folderID) {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100000 + 1); // generer et tilfeldig tall mellom
                                                                            // [1-100000 ] for å brukes til primary key
        System.out.println("Her er trådene som eksisterer");
        InfoCtrl threadCtrl = new InfoCtrl();
        threadCtrl.connect();
        ArrayList<Thread> threads = threadCtrl.getThreads(folderID);
        for (Thread thread : threads) {
            System.out.println(thread.getPostID() + "     " + thread.getHeader());
        }
        System.out.println("Skriv inn ID på tråden du ønsker å svare på: ");
        int threadID = scannerObject.nextInt();
        scannerObject.nextLine();
        System.out.println("Skriv inn svar: ");
        String content = scannerObject.nextLine();

        UseCase3Ctrl useCase3Ctrl = new UseCase3Ctrl();
        useCase3Ctrl.connect();
        try {
            useCase3Ctrl.startReply();
            useCase3Ctrl.makeReply(randomNum, 1, content, userID, threadID); // postID(1) må genereres hver gang og
            // userID(4) kan
            // være user.getUserID()

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void searchFor(String keyword, int courseID) {
        UseCase4Ctrl searchController = new UseCase4Ctrl();
        ArrayList<Thread> searchedThreads = searchController.search(keyword, courseID);
        System.out.println("Søket ditt ga følgende resultater: ");
        for (Thread thread : searchedThreads) {
            System.out.println(thread.getPostID() + "     " + thread.getHeader());
        }

    }
}
