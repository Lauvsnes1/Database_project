
public class Course {
    //Klasse for å lagre informasjon om course objekter
    int courseID;
    String courseName;
    String term;
    int canBeAnonymous;
    int userID;

    Course(int courseID, String courseName, String term, int canBeAnonymous, int userID) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.term = term;
        this.canBeAnonymous = canBeAnonymous;
        this.userID = userID;
    }

    Course() {

    }

    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

}
