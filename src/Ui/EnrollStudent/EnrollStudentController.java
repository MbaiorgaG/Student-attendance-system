package Ui.EnrollStudent;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EnrollStudentController implements Initializable {



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
    private JFXTextField date;

    @FXML
    private JFXDatePicker dob;

    @FXML
    private JFXComboBox<?> sex;

    @FXML
    private JFXComboBox<?> levelofstudy;

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

    @FXML
    void deletePrint(ActionEvent event) {

    }

    @FXML
    void doCancel(ActionEvent event) {

    }

    @FXML
    void donew(ActionEvent event) {

    }

    @FXML
    void modify(ActionEvent event) {

    }

    @FXML
    void verifyFinger(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
