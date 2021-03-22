import java.sql.PreparedStatement;

public class UseCase2Ctrl extends DBConn {

    private PreparedStatement threadStatement;
    private int postID;

    public void startThread() {
        try {
            threadStatement = conn.prepareStatement("INSERT INTO thread VALUES ((?),(?),(?),(?),(?),(?),(?))");
        } catch (Exception e) {
            System.out.println("Error during prepare of insert values into thread");

        }

    }

    public void makeThread(int postID, int anonymous, String content, String tag, String header, int userID,
            int folderID) {
        try {
            threadStatement.setInt(1, postID);
            threadStatement.setInt(2, anonymous);
            threadStatement.setString(3, content);
            threadStatement.setString(4, tag);
            threadStatement.setString(5, header);
            threadStatement.setInt(6, userID);
            threadStatement.setInt(7, folderID);
            threadStatement.execute();
            System.out.println("Post opprettet vellykket!");

        } catch (Exception e) {
            System.out.println("Error when post created");
            e.printStackTrace();
        }
    }

}
