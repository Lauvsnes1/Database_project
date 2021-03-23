import java.sql.*;

public class UseCase1Ctrl extends DBConn {
    /*
     * Denne klassen brukes til å løse useCase 1 Her sjekkes det om den unike
     * kombinasjonen av e-post og passord finnes i database og returnere det som et
     * User-objekt
     */

    protected User authUser(String Email, String password) {
        User user = new User();

        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM users WHERE Email = " + Email + "AND Pword = " + password;
            ResultSet result = statement.executeQuery(query);
            // Lager et resultset med en spørring som henter ut brukere med brukernavnet og
            // passordet autUser ble kalt med
            while (result.next()) {
                /*
                 * Hvis resultsettet ikke er tomt, lager vi her en ny bruker og returnerer den.
                 * Siden vi ikke har funksjonalitet for å opprette brukere, velger vi å ikke
                 * skrive kode som sjekker om to brukere i databasen har samme navn og passord,
                 * og regner med at Resultset result enten vil inneholde 0 eller 1 bruker.
                 */
                User existingUser = new User(result.getInt("UserID"), result.getString("Email"),
                        result.getString("Pword"), result.getString("UserType"));

                return existingUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
