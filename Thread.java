public class Thread {
    int postID;
    int anonymous;
    int userID;
    int folderID;
    String header;
    String content;
    String tag;

    Thread() {

    }

    Thread(int postID, int anonymous, String content, String tag, String header, int userID, int folderID) {
        this.postID = postID;
        this.anonymous = anonymous;
        this.header = header;
        this.content = content;
        this.tag = tag;
        this.userID = userID;
        this.folderID = folderID;
    }

    public int getPostID() {
        return postID;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

}
