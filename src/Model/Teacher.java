package Model;

public class Teacher extends Person {
    private String Pass;
    private String department;
    private String teacher_id;
    private String course_id;

    public Teacher(String teacher_id,String fullname, String pass,  String gender, String email, long phone, String department) {
        this.teacher_id = teacher_id;
        this.fullname = fullname;
        Pass = pass;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.department = department;
    }

    public Teacher(String teacher_id,String fullname) {
        this.teacher_id = teacher_id;
        this.fullname = fullname;
    }

    public Teacher( String fullname, String teacher_id, String department) {
        this.fullname = fullname;
        this.teacher_id = teacher_id;
        this.department = department;
    }

    public Teacher(String course_id) {
        this.course_id = course_id;
    }


    public String getPass() {
        return Pass;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getDepartment() {
        return department;
    }
}
