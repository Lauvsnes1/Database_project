import java.sql.*;
import java.util.*;

public class UseCase5Ctrl extends DBConn {

    private PreparedStatement replyStatement;

    protected void startViewed() {
        try {
            replyStatement = conn.prepareStatement("INSERT INTO viewed VALUES ((?),(?))");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewPost(int postID, int userID) {
        try {
            replyStatement.setInt(1, postID);
            replyStatement.setInt(2, userID);
            replyStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Viewed> postsRead() {
        ArrayList<Viewed> views = new ArrayList<Viewed>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT Email,COUNT(PostID) FROM users LEFT OUTER JOIN viewed ON users.UserID = viewed.UserID GROUP BY users.UserID ORDER BY COUNT(PostID) DESC;";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                views.add(new Viewed(result.getString("Email"), result.getInt("COUNT(PostID)")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return views;
    }

}
