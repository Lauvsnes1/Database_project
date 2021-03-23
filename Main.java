import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    private static int userID;
    private static String userType;

    public static Scanner scannerObject = new Scanner(System.in);

    public static void main(String[] args) {

        while (logInn()) {
            System.out.println("\nTast 1 for å lese statistikk(kun for instruktører), tast 0 for å gå videre: ");
            int choice = scannerObject.nextInt();
            if (choice == 1 && userType.matches("instructor")) {
                displayStatistics();

            } else if (choice == 0) {

                chooseCourse(userID);

                System.out.println("\nSkriv inn inn ID på kurset du ønsker å gå inn på: ");
                int courseID = scannerObject.nextInt();
                chooseFolder(courseID);

                System.out.println("\nTrykk 1 for å søke etter tråd eller trykk 0 for å velge mappe");
                int choice2 = scannerObject.nextInt();
                if (choice2 == 1) {
                    scannerObject.nextLine();
                    System.out.println("\nSøkeord: ");
                    String keyword = scannerObject.nextLine();
                    searchFor(keyword, courseID);

                } else if (choice2 == 0) {
                    System.out.println("\nSkriv inn inn ID på mappen du ønsker å gå inn i: ");
                    int folderID = scannerObject.nextInt();

                    System.out.println("\nTrykk 0 for å opprette tråd, 1 for å opprette svar og 2 for å lese");
                    int choice3 = scannerObject.nextInt();
                    if (choice3 == 0) {
                        createPost(folderID);
                    } else if (choice3 == 1) {
                        replyTo(folderID);
                    } else if (choice3 == 2) {
                        scannerObject.nextLine();
                        chooseThread(folderID);
                        System.out.println("\nSkriv inn ID på posten du ønsker å lese: ");
                        int postID = scannerObject.nextInt();
                        readThread(postID, userID);

                    }

                }
            } else {
                System.out.println("\nDu har ikke tilgang til å lese statistikk");
            }
            break;
        }

    }

    private static boolean logInn() {

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
            System.out.println("\nDu er logget inn!\n\nVelkommen, " + userType + " " + Email);
            return true;
        } else {
            System.out.println("Feil passord eller brukerernavn");
        }
        return false;

    }

    private static void chooseCourse(int userID) {
        System.out.println("\nHer er alle kursene du er medlemm i: \n");// Display all courses:
        InfoCtrl courseCtrl = new InfoCtrl();
        courseCtrl.connect();
        ArrayList<Course> courses = courseCtrl.getCourses(userID);
        for (Course course : courses) {
            System.out.println(course.getCourseID() + "    " + course.getCourseName());

        }
    }

    private static void chooseFolder(int courseID) {
        System.out.println("\nHer er alle mappene som finnes: ");
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
        System.out.println("\nSkriv inn tittel på tråden ditt: ");
        String header = scannerObject.nextLine();
        System.out.println("\nSkriv inn innholdet på tråden: ");
        String content = scannerObject.nextLine();
        System.out.println("Trykk på tallet for riktig tag:\n ");
        System.out.println(
                "1: question\n2: announcement\n3: homework\n4: homework solution\n5: lecture notes\n6: general announcement\n");
        int input = scannerObject.nextInt();
        String tag = "";
        switch (input) {
        case 1:
            tag = "question";
            break;
        case 2:
            tag = "announcement";
            break;
        case 3:
            tag = "homework";
            break;
        case 4:
            tag = "homework solution";
            break;
        case 5:
            tag = "lecture notes";
            break;
        case 6:
            tag = "genereal anouncement";
            break;

        }
        try {
            useCase2Ctrl.startThread();
            useCase2Ctrl.makeThread(randomNum, 1, content, tag, header, userID, folderID);
            readThread(randomNum, userID);

        } catch (Exception e) {

        }

    }

    private static void replyTo(int folderID) {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 100000 + 1); // generer et tilfeldig tall mellom
                                                                            // [1-100000 ] for å brukes til primary key
        System.out.println("\nHer er trådene som finnes i mappen: ");
        InfoCtrl threadCtrl = new InfoCtrl();
        threadCtrl.connect();
        ArrayList<Thread> threads = threadCtrl.getThreads(folderID);
        for (Thread thread : threads) {
            System.out.println(thread.getPostID() + "\t" + thread.getHeader() + "\t" + thread.getContent());
        }
        System.out.println("\nSkriv inn ID på tråden du ønsker å svare på: ");
        int threadID = scannerObject.nextInt();
        scannerObject.nextLine();
        System.out.println("\nSkriv inn svar: \n");
        String content = scannerObject.nextLine();
        readThread(threadID, userID);

        UseCase3Ctrl useCase3Ctrl = new UseCase3Ctrl();
        useCase3Ctrl.connect();

        try {
            useCase3Ctrl.startReply();
            useCase3Ctrl.makeReply(randomNum, 1, content, userID, threadID);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void searchFor(String keyword, int courseID) {
        UseCase4Ctrl searchController = new UseCase4Ctrl();
        searchController.connect();
        ArrayList<Thread> searchedThreads = searchController.search(keyword, courseID);
        System.out.println("\nSøket ditt ga følgende resultater: \n");
        for (Thread thread : searchedThreads) {
            System.out.printf("%-10d%-40s%-100s\n", thread.getPostID(), thread.getHeader(), thread.getContent());

        }
        System.out.println("\nSkriv inn tråden du vil gå inn på:\n ");
        int postID = scannerObject.nextInt();
        readThread(postID, userID);

    }

    private static void readThread(int postID, int userID) {
        UseCase5Ctrl viewCtrl = new UseCase5Ctrl();
        viewCtrl.connect();
        try {
            viewCtrl.startViewed();
            viewCtrl.viewPost(postID, userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        InfoCtrl threadCtrl = new InfoCtrl();
        threadCtrl.connect();
        Thread thread = threadCtrl.getThread(postID);
        System.out.println(
                "\nHer er tråden: \n" + thread.getPostID() + "\t" + thread.getHeader() + "\t" + thread.getContent());
        followUp(postID, userID);
    }

    private static void chooseThread(int folderID) {
        System.out.println("\nHer er trådene som finnes i mappen: ");
        InfoCtrl threadCtrl = new InfoCtrl();
        threadCtrl.connect();
        ArrayList<Thread> threads = threadCtrl.getThreads(folderID);
        for (Thread thread : threads) {
            System.out.println(thread.getPostID() + "\t" + thread.getHeader());
        }

    }

    private static void displayStatistics() {
        UseCase5Ctrl readCtrl = new UseCase5Ctrl();
        readCtrl.connect();
        ArrayList<Viewed> views = readCtrl.postsRead();
        readCtrl.postsCreated(views); // Registrer også anntall poster opprettet ved å matche med e-post
        // System.out.println("Email:\t\t\tPosts Read:\t\t\tPosts Created:\n");
        System.out.printf("%-22s%-22s%-22s\n", "Email", "Views", "Posts created: \n");
        for (Viewed viewed : views) {
            System.out.printf("%-22s%-22d%-22d\n", viewed.getEmail(), viewed.getCount(), viewed.getPostsCreated());

        }
        System.out.println("\n");
    }

    private static void followUp(int postID, int userID) {
        System.out.println("\nTast 1 hvis du vil svare på tråden(e) og 0 for å avslutte:\n ");
        int ans = scannerObject.nextInt();
        if (ans == 1) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 100000 + 1);
            System.out.println("Skriv inn svaret: ");
            scannerObject.nextLine();
            String content = scannerObject.nextLine();
            UseCase3Ctrl replyController = new UseCase3Ctrl();
            replyController.connect();

            try {
                replyController.startReply();
                replyController.makeReply(randomNum, 0, content, userID, postID);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
