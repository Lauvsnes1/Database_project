import java.sql.*;
import java.util.*;

public class UseCase5Ctrl extends DBConn {
    /*
     * Denne klassen brukes til loggføre brukerens aktivitet i databasen, slik at
     * det kan genereres statistikk
     */
    private PreparedStatement replyStatement;

    // Starter en statement
    protec ted

    void startViewed() {
        try {
            replyStatement = conn.prepareStatement("INSERT INTO viewed VALUES ((?),(?))");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Registrerer hver gang en bruker har sett én post og putter det inn i
    // koblingstabellen viewed
    public void viewPost(int postID, int userID) {
        try {
            replyStatement.setInt(1, postID);
            replyStatement.setInt(2, userID);
            replyStatement.execute();

        } catch (Exception e) {
            System.out.println("\n Du har sett denne posten før");
        }

    }

    /*
     * Samler data om hvor mange poster hver enkelt bruker har lest ved å joine
     * users med viewed - tabellen. Returnerer én liste med viewved-objekter som er
     * et objekt med en e-mail, poster sett og poster laget
     */
    public ArrayList<Viewed> postsRead() {
        ArrayList<Viewed> views = new ArrayList<Viewed>();
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT Email,COUNT(PostID) FROM users LEFT OUTER JOIN viewed ON users.UserID = viewed.UserID GROUP BY users.UserID ORDER BY COUNT(PostID) DESC";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                views.add(new Viewed(result.getString("Email"), result.getInt("COUNT(PostID)"), 0));
            }
            //Poster laget settet til 0 her

        } catch (Exception e) {
            e.printStackTrace();
        }
        return views;
    }

    /*
     * Finner hvor mange poster hver bruker har opprettet ved å joine users og post
     * tabellen. Deretter tar funksjonen inn lista fra postRead() og legger til hvor
     * mange poster hver bruker har laget
     */
    public void postsCreated(ArrayList<Viewed> views) {
        try {
            Statement statement2 = conn.createStatement();
            String query2 = "SELECT Email,COUNT(PostID) AS AntallOpprettet FROM users LEFT OUTER JOIN post ON post.UserID = users.UserID GROUP BY users.UserID";
            ResultSet result2 = statement2.executeQuery(query2);

            while (result2.next()) {
                for (Viewed viewed : views) {
                    if (viewed.getEmail().matches(result2.getString("Email"))) {
                        viewed.setPostsCreated(result2.getInt("AntallOpprettet"));
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
