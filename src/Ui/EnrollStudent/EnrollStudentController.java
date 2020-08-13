package Ui.EnrollStudent;

import Model.Student;
import Utils.AlertMaker;
import com.jfoenix.controls.*;
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
    private ImageView fingerprint;

    @FXML
    private JFXButton take_button;

    @FXML
    private JFXButton re_take_button;

    @FXML
    private Label flabel;

    @FXML
    private JFXButton newButton;

    @FXML
    private JFXButton modifyButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton closeButton;

    @FXML
    private Label datalabel;

    @FXML
    private JFXSnackbar snackbar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userGender();
        studentLevel();
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

    public void saveNewStuden(ActionEvent actionEvent) throws ParseException {
        String matricNo = matric.getText();
        String firstName = firstname.getText();
        String surName = surname.getText();
        String dept = department.getText();
        String phoneNo = phone.getText();
        LocalDate date = dob.getValue();
        boolean isSexSelected = sex.getSelectionModel().isEmpty();
        boolean isLevelSelected = levelofstudy.getSelectionModel().isEmpty();

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
        if (phoneNo.isEmpty()) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(),
                    "Invalid phone number", "Please enter a valid Phone Number.");
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

        //Search the database if the student already exist
        if (DataHelper.isStudentExists(matricNo)) {
            AlertMaker.showMaterialDialog(rootPane, root, new ArrayList<>(), "Duplicate Matriculation details",
                    "This student exist.\nPlease enroll an new student");
            return;
        }

        JFXDatePicker datePicker = new JFXDatePicker(date);
        String date1 = datePicker.getValue().toString();

        captureStudent();


        if(!date1.isEmpty()){
            Student student = new Student(matricNo,firstName,surName,sex.getValue(),
                    0,Collections.singletonList("CPT211,CPT112,CPT332,CPT443"),
                    0,0,
                   dept,date1,phoneNo,levelofstudy.getValue());

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

    private void captureStudent() {


    }

    private void clearEntries() {
            matric.clear();
            firstname.clear();
            surname.clear();
            department.clear();
            phone.clear();
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

    }



}
