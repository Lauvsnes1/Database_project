import java.sql.*;
import java.util.*;

public class UseCase4Ctrl extends DBConn {
    /*
     * Denne klasssen brukes til å løse UseCase 4, her hentes det ut informasjon som
     * matcher keyword opp mot den valgte mappen
     */

    // En arraylist med Threads som tar inn søkeordet og IDen til faget man søker i
    protected ArrayList<Thread> search(String keyword, int courseID) {
        ArrayList<Thread> threadList = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            //query som henter ut alle rader der Thread-headeren inneholder den kombinasjonen av bokstaver søkeordet består av
            String query = "SELECT * FROM thread NATURAL INNER JOIN folder INNER JOIN course ON course.CourseID = folder.CourseID WHERE folder.CourseID= "
                    + courseID + " AND Header LIKE '%" + keyword + "%'";
            ResultSet result = stat
                wh ile (result.next()) {
            //legger til threaden(e) som ble hentet i arraylisten 
                threadList.add(new Thread(result.getInt("PostID"), result.getInt("Anonymous"),
                        result.getString("Content"), result.getString("Tag"), result.getString("Header"),
                        result.getInt("UserID"), result.getInt("FolderID")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        //returnerer arraylisten med threadsene som inneholdt søkeordet
        return threadList;

    }
}
