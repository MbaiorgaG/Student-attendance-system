package Ui.EnrollStudent;

import Model.Attendance;
import Model.Course;
import Model.Student;
import Ui.Enrollment.UareUSampleJava;
import Utils.AlertMaker;
import com.jfoenix.controls.*;
import dbConnection.Connect;
import dbConnection.DataHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class EnrollStudentController implements Initializable {

    @FXML
    public StackPane rootPane;
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField matric;

    @FXML
    private JFXTextField surname;

    @FXML
    private JFXTextField firstname;

    @FXML
    private JFXTextField department;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXDatePicker dob;

    @FXML
    private JFXComboBox<String> sex;


    @FXML
    private JFXComboBox<String> levelofstudy;

    @FXML
    private static ImageView fingerprint;

    @FXML
    private JFXButton take_button;

    @FXML
    private JFXButton re_take_button;

    @FXML
    private JFXComboBox<Course> select_course;

    @FXML
    private JFXButton newButton;

    @FXML
    private JFXButton modifyButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton closeButton;

    @FXML
    private JFXTimePicker lect_start_time;

    @FXML
    private JFXTimePicker lect_end_time;

    @FXML
    private Label datalabel;

    @FXML
    private JFXSnackbar snackbar;


    public static String matric_id;
    String matricNo;
    String firstName;
    String surName;
    String dept;
    LocalDate date;
    UareUSampleJava uareUSampleJava;
    // get connection
    private static final Connection dbConnection = Connect.getConnect();

    public static void setMatric_id(String matric_id) {
        EnrollStudentController.matric_id = matric_id;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userGender();
        studentLevel();
        try {
            loadCourse();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private JFXComboBox<Course> loadCourse() throws SQLException {
        select_course.setPromptText("Select Course Lecturer");
        select_course.setLabelFloat(false);
        List<Course> list;
        list = getCourseId(loadCourseMethod());
        ObservableList<Course> courseOList = FXCollections.observableArrayList(list);
        select_course.setItems(courseOList);
        return select_course;
    }

    private List<Course> loadCourseMethod() throws SQLException {
        List<Course> courses = new ArrayList<>();
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM Course";
            PreparedStatement stmt = Objects.requireNonNull(dbConnection).prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()){
                courses.add(new Course(rs.getString("course_id"),
                        rs.getString("course_title")));
            }
            getCourseId(courses);

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Course");
            alert.setHeaderText("There are no Courses in the department!");
            alert.show();
        }finally {
            rs.close();
        }
        return courses;
    }

    private List getCourseId(List<Course> courses) {
        List<String>  details = new ArrayList<>();
        for(Course item:courses){
            details.add(item.getCourseId());
        }
        return details;
    }
    private String getCourseTitle(String ss) throws SQLException {
        String title = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT course_title FROM Course WHERE course_id='"+ss+"'";
            PreparedStatement stmt = Objects.requireNonNull(dbConnection).prepareStatement(sql);
            rs = stmt.executeQuery();
            title = rs.getString("course_title");
            return title;

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid Course");
            alert.setHeaderText("Course dose not exist in the department!");
            alert.show();
        }finally {
            rs.close();
        }
        return title;
    }


    private JFXComboBox<String>  studentLevel() {
        sex.setPromptText("Select Gender");
        sex.setLabelFloat(false);
        //Retrieve courses from table
        List<String> studentSex = Arrays.asList("Male", "Female", "Marlian");
        ObservableList<String> stsex = FXCollections.observableArrayList(studentSex);
        sex.setItems(stsex);
        return sex;
    }

    private JFXComboBox<String> userGender() {
        levelofstudy.setPromptText("Select Level");
        levelofstudy.setLabelFloat(false);
        //Retrieve courses from table
        List<String> studentSex = Arrays.asList("100 level", "200 level ", "300 level",
                "400 level","500 level","600 level", "Not a Student");
        ObservableList<String> stsex = FXCollections.observableArrayList(studentSex);
        levelofstudy.setItems(stsex);
        return levelofstudy;
    }

    public void saveNewStuden(ActionEvent actionEvent) throws ParseException, SQLException {
        matricNo = matric.getText();
        firstName = firstname.getText();
        surName = surname.getText();
        dept = department.getText();
        date = dob.getValue();
        boolean isSexSelected = sex.getSelectionModel().isEmpty();
        boolean isLevelSelected = levelofstudy.getSelectionModel().isEmpty();
        JFXDatePicker datePicker = new JFXDatePicker(date);
        String date1 = datePicker.getValue().toString();
        if (matricNo.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Matric Number", "Please enter a valid Matric Number.");
            return;
        }
        if (firstName.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Name", "Please enter a valid Name.");
            return;
        }
        if (surName.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Name", "Please enter a valid Name.");
            return;
        }
        if (dept.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Department", "Please enter a valid department.");
            return;
        }
        if (select_course.getValue() == null) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Course selected", "Please select a valid course.");
            return;
        }

        if (dob.getValue() == null) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Date", "Please enter a valid Date.");
            return;
        }

        if (isSexSelected) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Gender Selected", "Please select yoor gender.");
            return;
        }

        if (isLevelSelected) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Level selected", "Selecte a valid level.");
            return;
        }


        String course_selected = String.valueOf(select_course.getValue());
        String level = levelofstudy.getValue();
        String student_name = firstName+" "+surName;

        if(!date1.isEmpty()){
            //Create a new object of type student
            Student student = new Student(matricNo,student_name,sex.getValue(),course_selected,
                   dept,date1,level);

            //Create a new object of type attendance.
            Attendance attendance = new Attendance(student_name,course_selected,0,level,matricNo);
            DataHelper.insertNewAttendance(attendance);

            boolean result = DataHelper.insertNewStudent(student);
            if (!result) {
                AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                        "New Student Enrollment", firstName+" "+surName + " has been added to the system");
                clearEntries();
            } else {
                AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                        "Failed to add new course",
                        "Check all the entries and try again");
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Date of Birth");
            alert.setHeaderText("Pick a date of Birth");
            alert.show();
        }
    }


    private void clearEntries() {
            matric.clear();
            firstname.clear();
            surname.clear();
            department.clear();
            levelofstudy.getSelectionModel().clearSelection();
    }


    @FXML
    void deletePrint(ActionEvent event) {

    }

    @FXML
    void doCancel(ActionEvent event) {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void modify(ActionEvent event) {

    }

    @FXML
    void verifyFinger(ActionEvent event) {
        matricNo = matric.getText();
        if (matricNo.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid Matric Number", "Please enter a valid Matric Number.");
            return;
        }
        setMatric_id(matricNo);
        uareUSampleJava = new UareUSampleJava();
        uareUSampleJava.main();
    }



}
