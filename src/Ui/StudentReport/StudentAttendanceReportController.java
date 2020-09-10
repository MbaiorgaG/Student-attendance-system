package Ui.StudentReport;

import Model.Attendance;
import Model.Course;
import Model.Student;
import Model.Teacher;
import com.jfoenix.controls.JFXComboBox;
import dbConnection.Connect;
import dbConnection.Operations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentAttendanceReportController implements Initializable {

    @FXML
    private Label lecturer_name;

    @FXML
    private JFXComboBox<String> course;

    @FXML
    private TableView<Attendance> list_table;

    @FXML
    private TableColumn<Attendance, String> matric_col;

    @FXML
    private TableColumn<Attendance, String> name_col;

    @FXML
    private TableColumn<Attendance, Integer> attendance_col;

    // get connection
    private static final Connection dbConnection = Connect.getConnect();

    // an observable list of students
    private ObservableList<Attendance> students;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pupolateLecturers();
    }


    private JFXComboBox<String> pupolateLecturers() {
        course.setPromptText("Select Subject");
        course.setLabelFloat(false);
        List<String> list = FXCollections.observableArrayList();
        loadCourse(list);
        ObservableList<String> courseOList = FXCollections.observableArrayList(list);
        course.setItems(courseOList);
        return course;
    }

    private List loadCourse(List<String> list) {
        list.clear();
        Course course1 = new Course();
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM Course";
            PreparedStatement stmt = Objects.requireNonNull(dbConnection).prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String courseId = rs.getString("course_id");
                String title = rs.getString("course_title");
                String pre = rs.getString("course_preq");
                String courseLec = rs.getString("teacher_id");

                course1.setCourseId(courseId);
                course1.setCourseTitle(title);
                course1.setCoursePrereq(pre);
                course1.setCourseLecturer(courseLec);

                list.add(courseId);

            }
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Lecturer");
            alert.setHeaderText("There are no report to display in the department!");
            alert.show();
        } finally {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }

    @FXML
    void select_course(ActionEvent event) {
        if (course.getValue() != null) {
            String course_id = course.getValue();
            String teacher_id = getLectId(course_id);//Get the teacher_id with course_id
            String course_lecturer = getCourseLect(teacher_id); // Get the lecturer with the teacher_id
            lecturer_name.setText(course_lecturer);
        }

        if (course.getValue() != null && lecturer_name.getText() != null) {
            String course_id = course.getValue();
            getAttendanceFromDb(course_id);
        }

        if(course.getValue() != null) {
            matric_col.setCellValueFactory(new PropertyValueFactory<>("matric_no"));
            name_col.setCellValueFactory(new PropertyValueFactory<>("student_name"));
            attendance_col.setCellValueFactory(new PropertyValueFactory<>("attendance"));
            List<Attendance> studentsAttendance = getAttendanceFromDb(course.getValue());
            students = FXCollections.observableArrayList(studentsAttendance);
            list_table.setItems(students);
            list_table.setEditable(true);
            list_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        }
    }

    private List<Attendance> getAttendanceFromDb(String course_id) {
        List<Attendance> listUsers = new ArrayList<>();
        ResultSet rs = null;
        try {
            String sqlStmt = "Select matric_no, student_name,attendance from attendance where course_id=?";
            PreparedStatement stmt = Objects.requireNonNull(dbConnection).prepareStatement(sqlStmt);
            stmt.setString(1, course_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                listUsers.add(new Attendance(rs.getString("matric_no"),
                        rs.getString("student_name"),
                        rs.getInt("attendance")));

            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listUsers;
    }


    private String getCourseLect(String teacher_id) {
        String courseLec = null;
        ResultSet rs = null;
        try {
            String query = "SELECT teacher_name FROM  Teachers WHERE teacher_id=?";
            PreparedStatement stmt = Objects.requireNonNull(dbConnection).prepareStatement(query);
            stmt.setString(1, teacher_id);
            rs = stmt.executeQuery();
            courseLec = rs.getString("teacher_name");

            rs.close();
            return courseLec;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Lecturer");
            alert.setHeaderText("There are no lecturers in the department!");
            alert.show();
        } finally {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return courseLec;
    }

    private String getLectId(String course_id) {
        String lect_id = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT teacher_id FROM Course WHERE course_id='" + course_id+ "'";
            PreparedStatement stmt = Objects.requireNonNull(dbConnection).prepareStatement(sql);
            rs = stmt.executeQuery();
            lect_id = rs.getString("teacher_id");
            rs.close();
            return lect_id;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lect_id;
    }

}
