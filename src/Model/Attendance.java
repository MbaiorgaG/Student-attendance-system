package Model;

public class Attendance {
    private String student_name;
    private String course_id;
    private int attendance;
    private String level;
    private String matric_no;

    public Attendance() {
    }

    public Attendance(String student_name, String course_id, int attendance, String level, String matric_no) {
        this.student_name = student_name;
        this.course_id = course_id;
        this.attendance = attendance;
        this.level = level;
        this.matric_no = matric_no;
    }

    public Attendance(String matric_no, String student_name, int attendance) {
        this.matric_no = matric_no;
        this.student_name = student_name;
        this.attendance = attendance;
    }


    public String getStudent_name() {
        return student_name;
    }

    public String getMatric_no() {
        return matric_no;
    }

    public void setMatric_no(String matric_no) {
        this.matric_no = matric_no;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
