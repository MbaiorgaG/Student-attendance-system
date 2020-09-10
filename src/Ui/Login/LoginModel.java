package Ui.Login;

import Model.Teacher;
import dbConnection.Connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class LoginModel {
    private static Teacher logged; // new teacher to store the logged one in
    // get connection
    private Connection con = Connect.getConnect();

    public static Teacher getLogged() {
        return logged;
    }

    private static void setLogged(ResultSet set) throws SQLException {
        // store the logged in user details in an object for later use

        logged = new Teacher(
                set.getString("teacher_id"),
                set.getString("teacher_name"),
                set.getString("password"),
                set.getString("gender"),
                set.getString("email"),
                set.getLong("phone"),
                set.getString("department"));
    }


    // check login details
    boolean isCorrect(String user_email, String password) throws SQLException {

        // if connection is closed get it again
        if (con.isClosed()) {
            con = Connect.getConnect();
        }
        PreparedStatement statement;
        ResultSet set;
        String query = "select * from Teachers where email = ? and password = ?";
        statement = Objects.requireNonNull(con).prepareStatement(query);
        statement.setString(1, user_email);
        statement.setString(2, password);
        set = statement.executeQuery();
        if (set.next()) {
            setLogged(set);
            statement.close();
            con.close();
            return true;
        } else return false;
    }
}
