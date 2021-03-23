public class User {
    // klasse for å lagre informasjon om User
    String email;
    int userID;
    String userType;
    String pword;

    public User(int userID, String email, String pword, String userType) {
        this.email = email;
        this.userID = userID;
        this.userType = userType;
        this.pword = pword;
    }

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return pword;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserType() {
        return userType;
    }

}
