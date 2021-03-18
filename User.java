public class User {
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
}
