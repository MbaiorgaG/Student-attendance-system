package Ui.ViewStudent;

import Model.Student;
import Model.Teacher;
import dbConnection.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

public class ViewAllStudentController implements Initializable {

    @FXML
    private TableView<Student> list_table;

    @FXML
    private TableColumn<Student, String> name_col;

    @FXML
    private TableColumn<Student, String> id_col;

    @FXML
    private TableColumn<Student, String> course_col;
    // get connection
    private static final Connection dbConnection = Connect.getConnect();
    private ObservableList<Student> students;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        name_col.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        id_col.setCellValueFactory(new PropertyValueFactory<>("matricNo"));
        course_col.setCellValueFactory(new PropertyValueFactory<>("levelOfStudy"));
        List<Student> studentsAttendance = loadAllTeachers();
        students = FXCollections.observableArrayList(studentsAttendance);
        list_table.setItems(students);
        list_table.setEditable(true);
        list_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
    private List loadAllTeachers() {
        List<Student> teacherList = new ArrayList<>();
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM Student";
            PreparedStatement stmt = Objects.requireNonNull(dbConnection).prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
                teacherList.add(new Student(
                        rs.getString("student_name"),
                        rs.getString("matric_no"),
                        rs.getString("stud_level")));

            }
            rs.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Select Lecturer");
            alert.setHeaderText("There are no students in the department!");
            alert.show();
        } finally {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return teacherList;
    }
}

