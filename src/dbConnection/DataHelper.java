package dbConnection;

import Model.Course;
import Model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class DataHelper {
    //    private final static Logger LOGGER = LogManager.getLogger(DatabaseHandler.class.getName());
     private static Connection connection = Connect.getConnect();


    public static boolean insertNewCourse(Course course) {

        checkConn();
        String query = "INSERT INTO Course(id,courseTitle,courseUnit,coursePrereq,courseLecturer) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pst = Objects.requireNonNull(connection).prepareStatement(query);
            // set the question marks in statement to these values
            pst.setString(1, course.getCourseId().toUpperCase());
            pst.setString(2, course.getCourseTitle().toUpperCase());
            pst.setString(3, course.getCourseUnit());
            pst.setString(4, course.getCoursePrereq());
            pst.setString(5, course.getCourseLecturer().toUpperCase());
            pst.execute();
            pst.close(); // close statement
            connection.close(); // close connection
          //  loadCourseList(list_table, course); // reload table after adding new course
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Add new Course");
//        alert.setHeaderText("Course added successfully!");
//        alert.show();
        return false;
    }



    public static void checkConn() {
        try {
            if(connection.isClosed()){
                connection = Connect.getConnect();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static boolean insertNewStudent(Student student) {
        checkConn();
        String query = "INSERT INTO student(matric_no,first_name,last_name,gender, absences,course, present, excuse,department,date_of_birth,phoneNo, stud_level) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, student.getMatricNo());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setString(4, student.getGender());
            statement.setInt(5, student.getAbsences());
            statement.setString(6, String.valueOf(student.getSubjects()));
            statement.setInt(7, student.getPresent());
            statement.setInt(8, student.getExcuse());
            statement.setString(9, student.getDepartment());
            statement.setString(10, student.getData_of_birth());
            statement.setString(11, student.getPhoneNo());
            statement.setString(12, student.getLevelOfStudy());
            statement.execute();
            statement.close(); // close statement
            connection.close(); // close connection
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        return false;
    }

    public static boolean isCourseExist(String id) {
        try {
            String checkstmt = "SELECT COUNT(*) FROM Course WHERE id=?";
            PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(checkstmt);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return (count > 0);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean isStudentExists(String id) {
        try {
            String checkstmt = "SELECT COUNT(*) FROM student WHERE id=?";
            PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(checkstmt);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                System.out.println(count);
                return (count > 0);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static ResultSet getBookInfoWithIssueData(String id) {
//        try {
//            String query = "SELECT BOOK.title, BOOK.author, BOOK.isAvail, ISSUE.issueTime FROM BOOK LEFT JOIN ISSUE on BOOK.id = ISSUE.bookID where BOOK.id = ?";
//            PreparedStatement stmt = DatabaseHandler.getInstance().getConnection().prepareStatement(query);
//            stmt.setString(1, id);
//            ResultSet rs = stmt.executeQuery();
//            return rs;
//        } catch (SQLException ex) {
//            LOGGER.log(Level.ERROR, "{}", ex);
//        }
        return null;
    }



//    public static boolean updateMailServerInfo(MailServerInfo mailServerInfo) {
//        try {
//            wipeTable("MAIL_SERVER_INFO");
//            PreparedStatement statement = DatabaseHandler.getInstance().getConnection().prepareStatement(
//                    "INSERT INTO MAIL_SERVER_INFO(server_name,server_port,user_email,user_password,ssl_enabled) VALUES(?,?,?,?,?)");
//            statement.setString(1, mailServerInfo.getMailServer());
//            statement.setInt(2, mailServerInfo.getPort());
//            statement.setString(3, mailServerInfo.getEmailID());
//            statement.setString(4, mailServerInfo.getPassword());
//            statement.setBoolean(5, mailServerInfo.getSslEnabled());
//            return statement.executeUpdate() > 0;
//        } catch (SQLException ex) {
//            LOGGER.log(Level.ERROR, "{}", ex);
//        }
//        return false;
//    }

//    public static MailServerInfo loadMailServerInfo() {
//        try {
//            String checkstmt = "SELECT * FROM MAIL_SERVER_INFO";
//            PreparedStatement stmt = DatabaseHandler.getInstance().getConnection().prepareStatement(checkstmt);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                String mailServer = rs.getString("server_name");
//                Integer port = rs.getInt("server_port");
//                String emailID = rs.getString("user_email");
//                String userPassword = rs.getString("user_password");
//                Boolean sslEnabled = rs.getBoolean("ssl_enabled");
//                return new MailServerInfo(mailServer, port, emailID, userPassword, sslEnabled);
//            }
//        } catch (SQLException ex) {
//            LOGGER.log(Level.ERROR, "{}", ex);
//        }
//        return null;
//    }
}
