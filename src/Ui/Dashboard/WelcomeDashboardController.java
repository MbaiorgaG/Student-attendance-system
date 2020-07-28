package Ui.Dashboard;

import Utils.StudentAttendanceUtils;
import javafx.event.ActionEvent;
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

    @FXML
    void addCourse(MouseEvent event) {

    }

    @FXML
    void enrollStudent(ActionEvent event) {
        StudentAttendanceUtils.loadWindow(getClass().getResource("/Ui/EnrollStudent/EnrollStudent.fxml"), "Enrol Student", null);
    }

    @FXML
    void startAttendance(MouseEvent event) {

    }

    @FXML
    void studentReport(MouseEvent event) {

    }

    @FXML
    void viewCourse(MouseEvent event) {

    }

    @FXML
    void viewStudent(MouseEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
