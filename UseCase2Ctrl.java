import java.sql.PreparedStatement;

public class UseCase2Ctrl extends DBConn {

    private PreparedStatement threadStatement;
    private PreparedStatement postStatement;
    private int postID;

    public void startThread() {
        try {
            threadStatement = conn.prepareStatement("INSERT INTO thread VALUES ((?),(?),(?),(?),(?),(?),(?))");
            postStatement = conn.prepareStatement("INSERT INTO post VALUES ((?),(?),(?),(?))");
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

            postStatement.setInt(1, postID);
            postStatement.setString(2, content);
            postStatement.setInt(3, anonymous);
            postStatement.setInt(4, userID);
            postStatement.execute();
            System.out.println("Post opprettet vellykket!");

        } catch (Exception e) {
            System.out.println("Error when post created");
            e.printStackTrace();
        }
    }

}
