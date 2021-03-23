public class Viewed {

    public int postsCreated;
    public int views;
    public String email;

    public Viewed() {

    }

    public Viewed(String email, int views, int postsCreated) {
        this.email = email;
        this.views = views;
        this.postsCreated = postsCreated;

    }

    public int getCount() {
        return views;
    }

    public String getEmail() {
        return email;
    }

    public int getPostsCreated() {
        return postsCreated;

    }

    public void setPostsCreated(int postsCreated) {
        this.postsCreated = postsCreated;
    }

}
