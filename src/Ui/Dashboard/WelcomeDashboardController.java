package Ui.Dashboard;

import Utils.StudentAttendanceUtils;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeDashboardController implements Initializable {

    @FXML
    public JFXButton add_course;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void enrollStudent(ActionEvent event) {
        StudentAttendanceUtils.loadWindow(getClass().getResource("/Ui/EnrollStudent/EnrollStudent.fxml"), "Enrol Student", null);
    }

    @FXML
    public void addCourse(ActionEvent actionEvent) {
        StudentAttendanceUtils.loadWindow(getClass().getResource("/Ui/AddCourse/AddCourse.fxml"), "Add Course", null);
    }

    @FXML
    public void viewCourse(ActionEvent actionEvent) {
        StudentAttendanceUtils.loadWindow(getClass().getResource("/Ui/ViewLecturers/ViewAllLecturers.fxml"), "View All Lecturers", null);
    }

    @FXML
    public void report(ActionEvent actionEvent) {
        StudentAttendanceUtils.loadWindow(getClass().getResource("/Ui/StudentReport/StudentAttendanceReport.fxml"), "Student Attendance Report", null);
    }

    @FXML
    public void startAttendance(ActionEvent actionEvent) {
        StudentAttendanceUtils.loadWindow(getClass().getResource("/Ui/StartAttendance/StartAttendance.fxml"), "Start Attendance", null);
    }

    @FXML
    public void ViewStudent(ActionEvent actionEvent) {
        StudentAttendanceUtils.loadWindow(getClass().getResource("/Ui/ViewStudent/ViewAllStudent.fxml"), "View All Students", null);
    }
}
