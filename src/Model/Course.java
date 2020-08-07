package Model;

public class Course {
    private String courseId;
    private String courseTitle;
    private String courseUnit;
    private String coursePrereq;
    private String courseLecturer;

    public Course(String courseId, String courseTitle, String courseUnit, String coursePrereq, String courseLecturer) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.courseUnit = courseUnit;
        this.coursePrereq = coursePrereq;
        this.courseLecturer = courseLecturer;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseUnit() {
        return courseUnit;
    }

    public void setCourseUnit(String courseUnit) {
        this.courseUnit = courseUnit;
    }

    public String getCoursePrereq() {
        return coursePrereq;
    }

    public void setCoursePrereq(String coursePrereq) {
        this.coursePrereq = coursePrereq;
    }

    public String getCourseLecturer() {
        return courseLecturer;
    }

    public void setCourseLecturer(String courseLecturer) {
        this.courseLecturer = courseLecturer;
    }
}
