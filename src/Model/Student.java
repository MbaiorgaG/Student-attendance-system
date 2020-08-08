package Model;

import javafx.scene.control.CheckBox;

import java.util.List;
import java.util.Map;

public class Student extends Person {
    private int absences; // number of times student was absent
    private String excuse;
    private CheckBox present = new CheckBox();
    private String bar_status;
    private String fingerImage;
    private String matricNo;
    private String department;

    // a constructor for the students list page
    public Student(String matricNo, String fname,String lname, String gender,
                   String mail, int absences, String bar_status,
                   List<String> subjects,
                   Boolean present, String excuse,
                   String fingerImage, String department) {

        this.matricNo = matricNo;
        this.firstName = fname;
        this.lastName = lname;
        this.gender = gender;
        this.email = mail;
        this.absences = absences;
        this.bar_status = bar_status;
        this.subjects = subjects;
        this.present.setSelected(present);
        this.excuse = excuse;
        this.fingerImage = fingerImage;
        this.department = department;
    }

    public int getAbsences() {
        return absences;
    }

    public void setAbsences(int absences) {
        this.absences = absences;
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

    public String getExcuse() {
        return excuse;
    }

    public void setExcuse(String excuse) {
        this.excuse = excuse;
    }

    public CheckBox getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present.setSelected(present);
    }

    public String getBar_status() {
        return bar_status;
    }

    public void setBar_status(String bar_status) {
        this.bar_status = bar_status;
    }

    @Override
    public List<String> getSubjects() {
        return super.getSubjects();
    }
}
