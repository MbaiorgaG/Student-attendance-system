package Ui.AboutLecturer;

import Model.Teacher;
import Ui.Login.LoginModel;
import dbConnection.Connect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AboutLecturerController implements Initializable {

    @FXML
    private ImageView avatar;
    @FXML
    private Label gender;

    @FXML
    private Label name;

    @FXML
    private Label ID;

    @FXML
    private Label email;

    @FXML
    private Label subjects;

    @FXML
    private Label number;
    private static Connection connection = Connect.getConnect();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Teacher teacher = LoginModel.getLogged(); // get logged in teacher from login model class

        // set the about info to that
        name.setText(teacher.getFullname());
        ID.setText(teacher.getTeacher_id());
        email.setText(teacher.getEmail());
        gender.setText(teacher.getGender());
        subjects.setText("");
        List<Teacher> subject;
        subject = getLectureSubject(teacher.getTeacher_id());
        for (Teacher name: subject){
            subjects.setText(subjects.getText() + name.getCourse_id() + ", ");
        }
        number.setText(String.valueOf(teacher.getPhone()));

        Image male = new Image("Ui/resources/male.png");
        Image female = new Image("Ui/resources/female.png");

        if (teacher.getGender().startsWith("F") || teacher.getGender().startsWith("f")) {
            avatar.setImage((female));
        } else avatar.setImage(male);

    }

    //Search the database and return the lecturer with the course he teaches.
    private List<Teacher> getLectureSubject(String teacher_id) {
        List<Teacher> listUsers = new ArrayList<>();
        ResultSet rs = null;
        try {
            String sqlStmt = "Select course_id from Course where teacher_id=?";
            PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sqlStmt);
            stmt.setString(1, teacher_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                listUsers.add(new Teacher(rs.getString("course_id")));
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


}
