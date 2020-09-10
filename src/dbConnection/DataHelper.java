package dbConnection;

import Model.Attendance;
import Model.Course;
import Model.Student;
import Ui.StartAttendance.StartAttendanceController;
import Utils.MessageBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataHelper {
     private static Connection connection = Connect.getConnect();


    public static boolean insertNewCourse(Course course) {

        checkConn();
        String query = "INSERT INTO Course(course_id,course_title,course_unit,course_preq,teacher_id) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pst = Objects.requireNonNull(connection).prepareStatement(query);
            // set the question marks in statement to these values
            pst.setString(1, course.getCourseId().toUpperCase());
            pst.setString(2, course.getCourseTitle().toUpperCase());
            pst.setString(3, course.getCourseUnit());
            pst.setString(4, course.getCoursePrereq());
            pst.setString(5, course.getCourseLecturer());
            pst.execute();
            pst.close(); // close statement
          //  loadCourseList(list_table, course); // reload table after adding new course
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insertNewAttendance(Attendance attendance) {
        checkConn();
        String query = "INSERT INTO Attendance(student_name,course_id,attendance,stud_level,matric_no) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pst = Objects.requireNonNull(connection).prepareStatement(query);
            // set the question marks in statement to these values
            pst.setString(1, attendance.getStudent_name());
            pst.setString(2, attendance.getCourse_id().toUpperCase());
            pst.setInt(3, attendance.getAttendance());
            pst.setString(4, attendance.getLevel());
            pst.setString(5, attendance.getMatric_no());
            pst.execute();
            pst.close(); // close statement
            //  loadCourseList(list_table, course); // reload table after adding new course
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean hasBiometricData(String matricNo) {
        ResultSet rs = null;
        try {
            String checkstmt = "SELECT * FROM student WHERE matric_no=?";
            PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(checkstmt);
            stmt.setString(1, matricNo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String bio_data = rs.getString("biometric");
                if(bio_data.equals(null))
                    return true;
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return false;
    }

    public  static boolean checkregistration(String matricNo,String courseId) {
        String cour_id = StartAttendanceController.courses_id;
            ResultSet rs = null;
            try {
                String checkstmt = "SELECT course_id, matric_no FROM attendance WHERE matric_no=?";
                PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(checkstmt);
                stmt.setString(1, matricNo);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("course_id");
                    String mat_no = rs.getString("matric_no");
                    if(id.equals(cour_id) && matricNo.equals(mat_no)){
                        return true;
                    }
                }
                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally {
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
            return false;

    }

    public void insertBiometric( byte[] print1, String userID)  {
        PreparedStatement pst = null;
        try {
        String query =
                "UPDATE student " +
                        "  set biometric =? " +
                        "where matric_no =?";

            pst = connection
                    .prepareStatement(query);

        pst.setBytes(1,print1);
        pst.setString(2, userID);
        pst.executeUpdate();
        pst.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        MessageBox.Warning("Data successfully captured.");
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

    public static boolean insertNewStudent(Student student)  {
        checkConn();
        String query = "INSERT INTO student(matric_no,student_name,gender, course_id,department,date_of_birth,stud_level) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, student.getMatricNo());
            statement.setString(2, student.getFullname());
            statement.setString(3, student.getGender());
            statement.setString(4, student.getCourse_id());
            statement.setString(5, student.getDepartment());
            statement.setString(6, student.getData_of_birth());
            statement.setString(7, student.getLevelOfStudy());
            statement.execute();
            statement.close(); // close statement
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        return false;
    }

    //Get single student biometric.
//    public Student GetStudentFPData(String mat_no) throws SQLException {
//        Student student = null;
//        ResultSet rs = null;
//        try {
//            String sqlStmt = "Select * from student where matric_no =?";
//            PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sqlStmt);
//            stmt.setString(1,mat_no);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                student = new Student(rs.getString("matric_no"), rs
//                        .getBytes("biometric"));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }finally {
//            rs.close();
//        }
//        return student;
//    }


    public static boolean isCourseExist(String id) throws SQLException {
        ResultSet rs = null;
        try {
            String checkstmt = "SELECT * FROM Course WHERE course_id=?";
            PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(checkstmt);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                String count = rs.getString("course_id");
                if(count.equals(id))
                    return true;
            }
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            rs.close();

        }
        return false;
    }

    public static boolean isStudentExists(String id) throws SQLException {
        ResultSet rs = null;
        try {
            String checkstmt = "SELECT COUNT(*) FROM student WHERE course_id=?";
            PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(checkstmt);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                System.out.println(count);
                return (count > 0);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            rs.close();
        }
        return false;
    }

    public List<Student> GetAllFPData()  {
        List<Student> listUsers = new ArrayList<>();
        ResultSet rs = null;
        try {
            String sqlStmt = "Select * from student";
            PreparedStatement stmt = Objects.requireNonNull(connection).prepareStatement(sqlStmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (rs.getBytes("biometric") != null)
                    listUsers.add(new Student(rs.getString("course_id"),
                            rs.getString("matric_no"),
                            rs.getBytes("biometric")));
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
