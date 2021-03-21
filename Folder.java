public class Folder {

    int folderID;
    String folderName;
    int courseID;

    Folder() {

    }

    Folder(int folderID, String folderName, int courseID) {
        this.folderID = folderID;
        this.folderName = folderName;
        this.courseID = courseID;
    }

    public int getFolderID() {
        return folderID;
    }

    public String getFolderName() {
        return folderName;
    }

}
