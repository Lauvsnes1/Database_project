import java.sql.*;
import java.util.*;

public class UseCase1Ctrl extends DBConn {

    private String Email;
    private String password;

    private User logInn(String email, String password) {
        String error = "her var det en feil gitt!";
        // this.userID = userID;
        // this.password = password;
        ArrayList<User> users = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            // String query = "SELECT UserID, Email, Pword, UserType FROM user WHERE Email =
            // " + Email + "AND Pword = "+ password;
            // String query = "SELECT UserID, Email, Pword, UserType FROM user WHERE Email =
            // (?) AND Pword = (?)";
            String query = "SELECT * FROM user";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                users.add(new User(result.getInt("UserID"), result.getString("Email"), result.getString("Pword"),
                        result.getString("UserType")));
                // return new User(result.getInt("UserID"), result.getString("Email"),
                // result.getString("Pword"), result.getString("UserType"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users.stream().filter(u -> u.email.equals(email) && u.pword.equals(password)).findAny().get();
    }

}
