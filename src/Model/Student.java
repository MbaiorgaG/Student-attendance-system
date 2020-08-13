package Model;

import java.util.List;

public class Student extends Person {
    private int absences;
    private int excuse;
    private int present;
    private String matricNo;
    private String department;
    private String data_of_birth;
    private String phoneNo;
    private String levelOfStudy;

    // a constructor for the students list page
    public Student(String matricNo, String fname, String lname, String gender,
                   int absences,List<String> subjects,int present, int excuse, String department,
                   String data_of_birth, String phoneNo,String levelOfStudy) {

        this.matricNo = matricNo;
        this.firstName = fname;
        this.lastName = lname;
        this.gender = gender;
        this.absences = absences;
        this.subjects = subjects;
        this.present = present;
        this.excuse = excuse;
        this.department = department;
        this.data_of_birth = data_of_birth;
        this.phoneNo = phoneNo;
        this.levelOfStudy = levelOfStudy;
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

    public int getAbsences() {
        return absences;
    }

    public void setAbsences(int absences) {
        this.absences = absences;
    }

    public int getExcuse() {
        return excuse;
    }

    public void setExcuse(int excuse) {
        this.excuse = excuse;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
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

    @Override
    public List<String> getSubjects() {
        return super.getSubjects();
    }
}
