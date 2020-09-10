package Ui.StartAttendance;

import Model.Course;
import Ui.Enrollment.UareUSampleJava;
import Utils.AlertMaker;
import com.jfoenix.controls.*;
import dbConnection.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartAttendanceController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXComboBox<String> select_course;

    @FXML
    private JFXTextField course_lect;
    @FXML
    private JFXTextField course_title;


    @FXML
    private JFXTimePicker lect_start_time;

    @FXML
    private JFXTimePicker lect_end_time;

    @FXML
    private JFXDatePicker lect_date;

    @FXML
    private JFXButton start_attedance;

    @FXML
    private JFXButton saveStudentBiometric;

    @FXML
    private JFXTextField student_mat;

    @FXML
    private JFXTextField student_name;

    @FXML
    private JFXTextArea student_excuse;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private JFXButton start_verification;

    @FXML
    private JFXTextArea lect_remark;

    @FXML
    private JFXButton end_attendance;

    @FXML
    private GridPane gridpan;

    List<Course> lectDetails = new ArrayList<>();
    UareUSampleJava uareUSampleJava;
    public static String courses_id;



    // get connection
    private static final Connection dbConnection = Connect.getConnect();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            pupolateLecturers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private JFXComboBox<String> pupolateLecturers() throws SQLException {
        select_course.setPromptText("Select Course");
        select_course.setLabelFloat(false);
        List<String> list = FXCollections.observableArrayList();
        loadCourse(list);
        ObservableList<String> courseOList = FXCollections.observableArrayList(list);
        select_course.setItems(courseOList);
        return select_course;
    }

    private List loadCourse(List<String> list) throws SQLException {
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
                lectDetails.add(course1);

            }
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Lecturer");
            alert.setHeaderText("There are no lecturers in the department!");
            alert.show();
        }finally {
            rs.close();
        }
        return list;
    }


    public StackPane getRootPane() {
        return rootPane;
    }

    public void setRootPane(StackPane rootPane) {
        this.rootPane = rootPane;
    }


    public static String getCourses_id() {
        return courses_id;
    }

    public static void setCourses_id(String courses_id) {
        StartAttendanceController.courses_id = courses_id;
    }

    @FXML
    void startAttendance(ActionEvent event) {
//        //Check all the other fields
        String lect = course_lect.getText();
        String coures = course_title.getText();
        if (select_course.getValue() == null) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Course selected", "Please select a valid course.");
            return;
        }
        if (lect.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Name", "Invalid lecturer name");
            return;
        }
        if (coures.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Name", "Invalid course selected");
            return;
        }
        if (lect_start_time.getValue() == null) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Time", "Invalid Lecture start Time selected");
            return;
        }
        if (lect_end_time.getValue() == null) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Time", "Invalid Lecture end Time selected");
            return;
        }

        if (lect_date.getValue() == null) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Date", "Invalid Lecture date selected");
            return;
        }

        start_verification.setDisable(false);
    }

    @FXML
    void endAttendance(ActionEvent event) {
        //Clear all the fields
        setCourses_id(null);
        start_verification.setDisable(true);
        lect_start_time.setValue(null);
        lect_end_time.setValue(null);
        lect_date.setValue(null);
    }

    @FXML
    void startVerification(ActionEvent event) {
        uareUSampleJava = new UareUSampleJava();
        setCourses_id(select_course.getValue());
        uareUSampleJava.main();

    }



    public void selectCourse(ActionEvent actionEvent) {
        if(select_course != null){
            String course_til = getCourseTitle(select_course.getValue());
            String teacher_id = getLectId(course_til);//Get the teacher_id with course_title
            String course_lecturer = getCourseLect(teacher_id); // Get the lecturer with the teacher_id
            course_title.setText(course_til);
            course_lect.setText(course_lecturer);
        }
    }



    private String getLectId(String ss) {
        String id = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT teacher_id FROM Course WHERE course_title='"+ss+"'";
            PreparedStatement stmt = Objects.requireNonNull(dbConnection).prepareStatement(sql);
            rs = stmt.executeQuery();
            id = rs.getString("teacher_id");
            rs.close();
            return id;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    private String getCourseTitle(String id) {
        String title = null;
        ResultSet rs = null;
        try {
            String query = "SELECT course_title FROM  Course WHERE course_id=?";
            PreparedStatement stmt = Objects.requireNonNull(dbConnection).prepareStatement(query);
            stmt.setString(1, id);
             rs = stmt.executeQuery();
            title = rs.getString("course_title");
            rs.close();
            return title;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Course Title");
            alert.setHeaderText("No course title in this department!");
            alert.show();
        }finally {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return title;
    }

    private String getCourseLect(String string) {
        String courseLec = null;
        ResultSet rs = null;
        try {
            String query = "SELECT teacher_name FROM  Teachers WHERE teacher_id=?";
            PreparedStatement stmt = Objects.requireNonNull(dbConnection).prepareStatement(query);
            stmt.setString(1, string);
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
        }finally {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return courseLec;
    }

}
