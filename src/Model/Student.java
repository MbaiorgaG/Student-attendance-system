package Model;

public class Student extends Person {
    private String matricNo;
    private String department;
    private String data_of_birth;
    private String phoneNo;
    private String levelOfStudy;
    byte[] biometric;
    private String course_id;

    public Student(String matricNo, String fullname, String gender,String course_id, String department,
                   String data_of_birth,String levelOfStudy) {
        this.matricNo = matricNo;
        this.fullname = fullname;
        this.gender = gender;
        this.department = department;
        this.data_of_birth = data_of_birth;
        this.course_id = course_id;
        this.levelOfStudy = levelOfStudy;
    }

    public Student(String course_id, String matric_no, byte[] biometrics) {
        this.matricNo = matric_no;
        this.biometric = biometrics;
        this.course_id = course_id;
    }

    public Student(String student_name, String matric_no, String stud_level) {
        this.fullname = student_name;
        this.matricNo = matric_no;
        this.levelOfStudy = stud_level;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getLevelOfStudy() {
        return levelOfStudy;
    }

    public void setLevelOfStudy(String levelOfStudy) {
        this.levelOfStudy = levelOfStudy;
    }

    public String getMatricNo() {
        return matricNo;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getData_of_birth() {
        return data_of_birth;
    }

    public void setData_of_birth(String data_of_birth) {
        this.data_of_birth = data_of_birth;
    }

    public byte[] getBiometric() {
        return biometric;
    }

    public void setBiometric(byte[] biometric) {
        this.biometric = biometric;
    }

}
