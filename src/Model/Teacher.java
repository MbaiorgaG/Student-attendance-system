package Model;

import java.util.Map;

public class Teacher extends Person {
    private String Pass; // login password
    private String XP; // experience (dunno why i called it XP)
    private String department;

    public Teacher(String fname, String lname, String pass, int ID, String gender, String email,
                   Map<String, String[]> subjects, String XP, long phone, String department) {
        this.firstName = fname;
        this.lastName = lname;
        Pass = pass;
        this.ID = ID;
        this.gender = gender;
        this.email = email;
        this.XP = XP;
        this.subjects = subjects;
        this.phone = phone;
        this.department = department;
    }

    public String getXP() {
        return XP;
    }

    public String getDepartment() {
        return department;
    }
}
