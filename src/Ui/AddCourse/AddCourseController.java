package Ui.AddCourse;

import Model.Course;
import Utils.AlertMaker;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dbConnection.Connect;
import dbConnection.DataHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AddCourseController implements Initializable {

    @FXML
    private JFXTextField courseCode;

    @FXML
    private JFXComboBox<String> courseUnitBox;

    @FXML
    private JFXTextField courseTitleText;

    @FXML
    private JFXComboBox<String> coursePrereqBox;

    @FXML
    private JFXComboBox<String> courseLectBox;

    @FXML
    private AnchorPane mainContainer;
    @FXML
    private StackPane rootPane;

    private final Boolean isInEditMode = Boolean.FALSE;


    @FXML
    private JFXButton newButton;

    @FXML
    private JFXButton saveButton;

    @FXML
    private JFXButton modifyButton;

    @FXML
    private JFXButton deleteButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton closeButton;

    // get connection
    private static final Connection dbConnection = Connect.getConnect();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpCourseUnit();
        setUpCoursePrereq();
        setUpCourseLecturer();
    }

    private JFXComboBox<String> setUpCourseLecturer() {
        courseLectBox.setPromptText("Select Course Lecturer");
        courseLectBox.setLabelFloat(false);
        //Retrive courses from table
        List<String> courseLect = Arrays.asList("Dr. Musa David","Prof. Ali Jacob","Mrs Adeboye Matthew","John Emeka");
        ObservableList<String> courseOList = FXCollections.observableArrayList(courseLect);
        courseLectBox.setItems(courseOList);
        return courseLectBox;
    }

    private JFXComboBox<String> setUpCoursePrereq() {
        coursePrereqBox.setPromptText("Select Course Prerequisite");
        coursePrereqBox.setLabelFloat(false);
        //Retrive courses from table
        List<String> coursePrereq = Arrays.asList("Physics","English","Chemistry","math");
        ObservableList<String> courseOList = FXCollections.observableArrayList(coursePrereq);
        coursePrereqBox.setItems(courseOList);
        return coursePrereqBox;
    }

    private JFXComboBox<String> setUpCourseUnit() {
        courseUnitBox.setPromptText("Select Course Unit");
        courseUnitBox.setLabelFloat(true);
        List<String> courseUnit = Arrays.asList("1","2","3","4");
        ObservableList<String> courseOList = FXCollections.observableArrayList(courseUnit);
        courseUnitBox.setItems(courseOList);
        return courseUnitBox;
    }


    @FXML
    void cancel(ActionEvent event) {

    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void createNew(ActionEvent event) {

    }

    @FXML
    void delete(ActionEvent event) {

    }

    @FXML
    void modify(ActionEvent event) {

    }

    @FXML
    void removeSpace(KeyEvent event) {

    }

    @FXML
    void save(ActionEvent event) {
        String courseID = courseCode.getText();
        String courseTitle = courseTitleText.getText();
        boolean isUnitSelected = courseUnitBox.getSelectionModel().isEmpty();
        boolean isPrereqSelected = coursePrereqBox.getSelectionModel().isEmpty();
        boolean isLecturereSelected = courseLectBox.getSelectionModel().isEmpty();


        if(courseID.isEmpty()){
            AlertMaker.showMaterialDialog(rootPane,mainContainer, new ArrayList<>(),
                    "Invalid Name","Please enter a valid name.");
            return;
        }
        if(isUnitSelected){
            AlertMaker.showMaterialDialog(rootPane,mainContainer, new ArrayList<>(),
                    "Invalid Course Unit","Please enter Information.");
            return;
        }
        if(courseTitle.isEmpty()){
            AlertMaker.showMaterialDialog(rootPane,mainContainer, new ArrayList<>(),
                    "Invalid Title","Please enter a valid Course Title.");
            return;
        }
        if(isPrereqSelected){
            AlertMaker.showMaterialDialog(rootPane,mainContainer, new ArrayList<>(),
                    "Invalid Course prerequisite","Please enter a valid Information.");
            return;
        }
        if(isLecturereSelected){
            AlertMaker.showMaterialDialog(rootPane,mainContainer, new ArrayList<>(),
                    "Select a Course Lecturer","Select a valid course lecturer.");
            return;
        }

        if (DataHelper.isCourseExist(courseID)) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Duplicate Course id",
                    "Course with same Course ID exists.\nPlease use new Course");
            return;
        }

        Course course = new Course(courseID, courseTitle, courseUnitBox.getValue(),
                coursePrereqBox.getValue(), courseLectBox.getValue());

        boolean result = DataHelper.insertNewCourse(course);
        if (!result) {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(),
                    "New course added", courseTitle + " has been added");
            clearEntries();
        } else {
            AlertMaker.showMaterialDialog(rootPane, mainContainer, new ArrayList<>(),
                    "Failed to add new course",
                    "Check all the entries and try again");
        }

    }

    private void clearEntries() {
        courseTitleText.clear();
        courseCode.clear();
        courseUnitBox.getSelectionModel().clearSelection();
        coursePrereqBox.getSelectionModel().clearSelection();
        courseLectBox.getSelectionModel().clearSelection();
    }

}
