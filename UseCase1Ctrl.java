import java.sql.*;
import java.util.*;

public class UseCase1Ctrl extends DBConn {

    private String Email;
    private String password;

    protected User authUser(String Email, String password) {
        String error = "her var det en feil gitt!";
        User user = new User();

        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM users WHERE Email = " + Email + "AND Pword = " + password;
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                User existingUser = new User(result.getInt("UserID"), result.getString("Email"),
                        result.getString("Pword"), result.getString("UserType"));

                return existingUser;
            }
        } catch (SQLException e) {
            System.out.println("Feil brukernavn eller passord");
            e.printStackTrace();
        }
        return user;
    }
}
