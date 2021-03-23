import java.sql.*;

public abstract class DBConn {
  // Abstrakt klasse som kobler til mysql serveren
  protected Connection conn;

  public DBConn() {

  }

  public void connect() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Prosjekt", "root", "fotball1");
    } catch (Exception e) {
      throw new RuntimeException("Unable to connect", e);
    }
  }

}
