import java.util.ArrayList;
import java.sql.*;

public class InfoCtrl extends DBConn {

    int userID;
    int folderID;
    int courseID;

    public ArrayList<Course> getCourses(int userID) {
        this.userID = userID;
        ArrayList<Course> courseList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM course INNER JOIN usersInCourse on course.CourseID = usersInCourse.CourseID WHERE usersInCourse.UserID = \""
                    + userID + "\"";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                courseList.add(new Course(result.getInt("CourseID"), result.getString("CourseName"),
                        result.getString("Term"), result.getInt("CanBeAnonymous"), result.getInt("UserID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseList;

    }

    public ArrayList<Folder> getFolder(int courseID) {
        this.courseID = courseID;
        ArrayList<Folder> folderList = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM folder NATURAL INNER JOIN course WHERE CourseID=" + courseID;
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                folderList.add(new Folder(result.getInt("FolderID"), result.getString("FolderName"),
                        result.getInt("CourseID")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return folderList;

    }

    public ArrayList<Thread> getThreads(int folderID) {
        this.folderID = folderID;
        ArrayList<Thread> threadList = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM thread NATURAL INNER JOIN folder WHERE FolderID=" + folderID;
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                threadList.add(new Thread(result.getInt("PostID"), result.getInt("Anonymous"),
                        result.getString("Content"), result.getString("Tag"), result.getString("Header"),
                        result.getInt("UserID"), result.getInt("FolderID")));
            }
        } catch (Exception e) {
            System.out.println("Did not find any threads");
            e.getStackTrace();
        }
        return threadList;

    }

    public Thread getThread(int postID) {
        Thread thread = new Thread();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM thread WHERE PostID=" + postID;
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Thread newThread = new Thread(result.getInt("PostID"), result.getInt("Anonymous"),
                        result.getString("Content"), result.getString("Tag"), result.getString("Header"),
                        result.getInt("UserID"), result.getInt("FolderID"));
                return newThread;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thread;
    }

}
