package Ui.ViewLecturers;

import Model.Attendance;
import Model.Course;
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

import java.awt.geom.AffineTransform;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ViewAllLecturersController  implements Initializable {

    @FXML
    private TableView<Teacher> list_table;

    @FXML
    private TableColumn<Teacher, String> name_col;

    @FXML
    private TableColumn<Teacher, String> id_col;

    @FXML
    private TableColumn<Teacher, String> course_col;

    // get connection
    private static final Connection dbConnection = Connect.getConnect();
    private ObservableList<Teacher> students;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        name_col.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        id_col.setCellValueFactory(new PropertyValueFactory<>("teacher_id"));
        course_col.setCellValueFactory(new PropertyValueFactory<>("department"));
        List<Teacher> studentsAttendance = loadAllTeachers();
        students = FXCollections.observableArrayList(studentsAttendance);
        list_table.setItems(students);
        list_table.setEditable(true);
        list_table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private List loadAllTeachers() {
       List<Teacher> teacherList = new ArrayList<>();
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM Teachers";
            PreparedStatement stmt = Objects.requireNonNull(dbConnection).prepareStatement(query);
            rs = stmt.executeQuery();
            while (rs.next()) {
               teacherList.add(new Teacher(
                       rs.getString("teacher_name"),
                       rs.getString("teacher_id"),
                       rs.getString("department")));

            }
            rs.close();

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
        return teacherList;
    }
}
