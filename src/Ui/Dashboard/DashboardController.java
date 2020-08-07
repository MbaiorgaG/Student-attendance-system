package Ui.Dashboard;

import Model.Teacher;
import Ui.Login.LoginModel;
import dbConnection.Connect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label Welcome;

    @FXML
    private Label stud_num;

    @FXML
    private Label classes_num;

    @FXML
    private Label abs_num;

    @FXML
    private Label atten_percent;

    @FXML
    private Label barred_num;

    // get logged in teacher
    private Teacher logged = LoginModel.getLogged();
    private Connection conn = Connect.getConnect(); // get connection to database

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {

        Welcome.setText("Welcome Back!");
        Welcome.setStyle("-fx-font-weight: Bold");

        if(String.valueOf(getStudentsNum()) != null){
            stud_num.setText(String.valueOf(getStudentsNum()));
        }

        if(String.valueOf(getClassesNum()) != null){
            classes_num.setText(String.valueOf(getClassesNum()));
        }

        if(String.valueOf(getAbsentStudentsNum()) != null){
            abs_num.setText(String.valueOf(getAbsentStudentsNum()));
        }

        double percent = getPercent(getAbsentStudentsNum(),getStudentsNum());

        atten_percent.setText(Math.round(percent) + "%");

        barred_num.setText(String.valueOf(getBarredStudentsNum()));

    }

    private double getPercent(double absentStudentsNum, double studentsNum) {
        return (100 - (absentStudentsNum/ studentsNum) * 100);
    }

    // get total number of students
    private int getStudentsNum() {
        checkConn(); // check if connection is available or not
        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(conn).createStatement()
                    .executeQuery(" select count(*) from '" + logged.getID() + "'");
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(rs).close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    // get number of barred students
    private int getBarredStudentsNum() {
        checkConn();
        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(conn).createStatement()
                    .executeQuery(" select count(*) from '" + logged.getID() + "' where bar = 'barred' "); // sql statement
            return rs.getInt(1); // get the statement output
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(rs).close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    // get number of absent students since last class
    private int getAbsentStudentsNum() {
        checkConn();
        ResultSet rs = null;
        try {
            rs = Objects.requireNonNull(conn).createStatement().
                    executeQuery(" select count(*) from '" + logged.getID() + "' where present = 0"); // sql statement
            return rs.getInt(1); // get the statement output
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                conn.close(); // make sure we close the connection
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0; //return 0 in case the try-catch block failed
    }

    // get number of classes.
    private int getClassesNum() {
        String subs; // temp for holding the value from sql
        checkConn();
        try {
            ResultSet rs = Objects.requireNonNull(conn).createStatement()
                    .executeQuery(" select * from Teachers where id = " + logged.getID()); // sql statement
            subs = rs.getString("subjects");
            // close query
            rs.close(); // close statement
            conn.close(); // close connection
            String[] subsArr = subs.split(" "); // split subjects (they're separated by space in database)
            return subsArr.length; // return the length of the splitted array ( yup i just made up the word splitted)
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; //return 0 in case the try-catch block failed
    }

    private void checkConn() {
        // if connection is closed get it again
        try {
            if (conn.isClosed()) conn = Connect.getConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
