package Ui.Dashboard;

import Utils.StudentAttendanceUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeDashboardController implements Initializable {

    @FXML
    private VBox welcome_dashboard;

    @FXML
    private Label Welcome;

    @FXML
    private HBox enroll_card;

    @FXML
    private HBox enroll_student;

    @FXML
    private Label enroll_stud;

    @FXML
    private HBox vieiw_stu_card;

    @FXML
    private Label view_student;

    @FXML
    private HBox start_attendance_card;

    @FXML
    private Label start_attendance;

    @FXML
    private HBox add_course_card;

    @FXML
    private Label add_course;

    @FXML
    private HBox view_course_card;

    @FXML
    private Label view_course;

    @FXML
    private HBox student_report_card;

    @FXML
    private Label stud_report;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Welcome.setText("Welcome Back!");
        Welcome.setStyle("-fx-font-weight: Bold");
    }

    @FXML
    void addCourse(MouseEvent event) {
        StudentAttendanceUtils.loadWindow(getClass().getResource(""), "Add Course", null);
    }

    @FXML
    void enrollStudent(MouseEvent event) {
        StudentAttendanceUtils.loadWindow(getClass().getResource(""), "Enrol Student", null);
    }

    @FXML
    void startAttendance(MouseEvent event) {
        StudentAttendanceUtils.loadWindow(getClass().getResource(""), "Start Ui.Attendance", null);
    }

    @FXML
    void studentReport(MouseEvent event) {
        StudentAttendanceUtils.loadWindow(getClass().getResource(""), "Student Report", null);
    }

    @FXML
    void viewCourse(MouseEvent event) {
        StudentAttendanceUtils.loadWindow(getClass().getResource(""), "View Course", null);
    }

    @FXML
    void viewStudent(MouseEvent event) {
        StudentAttendanceUtils.loadWindow(getClass().getResource(""), "View Student", null);
    }


}
