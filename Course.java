
public class Course {
    int courseID;
    String courseName;
    String term;
    int canBeAnonymous;
    int userID;// the constructor of the course

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
