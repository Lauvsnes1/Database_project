public class Reply {

    int postID;
    boolean anonymous;
    String header;
    String content;

    Reply(int postID, boolean anonymous, String header, String content) {
        this.postID = postID;
        this.anonymous = anonymous;
        this.header = header;
        this.content = content;

    }

    Reply() {

    }

}
