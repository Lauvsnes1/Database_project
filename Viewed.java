public class Viewed {

    public int count;
    public String email;

    Viewed() {

    }

    Viewed(String email, int count) {
        this.email = email;
        this.count = count;

    }

    public int getUserID() {
        return userID;
    }

    public int getCount() {
        return count;
    }

    public String getEmail() {
        return email;
    }

}
