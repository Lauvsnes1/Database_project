import java.sql.*;
import java.util.*;

public class UseCase4Ctrl extends DBConn {

    protected ArrayList<Thread> search(String keyword, int courseID) {
        ArrayList<Thread> threadList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            System.out.println("kom hit");
            String query = "SELECT * FROM thread NATURAL INNER JOIN folder INNER JOIN course ON course.CourseID = folder.CourseID WHERE folder.CourseID="
                    + courseID + "AND title LIKE '%" + keyword + "%'";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                threadList.add(new Thread(result.getInt("PostID"), result.getInt("Anonymous"),
                        result.getString("Content"), result.getString("Tag"), result.getString("Header"),
                        result.getInt("UserID"), result.getInt("FolderID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return threadList;

    }
}
