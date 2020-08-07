package Ui.Login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    // create an object of login class
    private LoginModel model = new LoginModel();

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField pass;

    @FXML
    private Label wrongData;

    @FXML
    private Button logBtn;


    // handle login button click event
    @FXML
    private void dataCheck() throws IOException, SQLException {
        // validation
        if (model.isCorrect(email.getText(), pass.getText())) {
//            Operations.loadPref(); // load settings
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Ui/Sidebar/Sidebar.fxml"));
            Parent homeParent = loader.load();
            Scene home = new Scene(homeParent);
            //This line gets the Stage information
            Stage window = (Stage) logBtn.getScene().getWindow();
            window.setTitle("Attendance System");
            window.setScene(home);
            window.show();
        } else {
            email.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
                if (!newValue) {
                    email.validate();
                }
            });
            // set the wrong data label to visible
            wrongData.setStyle("-fx-font-weight: bold");
            wrongData.setVisible(true);
        }
    }

    @FXML
    public void HandleEnter(KeyEvent event) throws IOException, SQLException {
        // make sure the button is enabled first before doing the action
        if (!logBtn.isDisabled()) if (event.getCode().toString().equals("ENTER")) dataCheck();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // disable login button if either text fields is empty (boolean binding)
        BooleanBinding booleanBind = Bindings.and(
                email.textProperty().isEmpty(),
                pass.textProperty().isEmpty());
        logBtn.disableProperty().bind(booleanBind);

        NumberValidator numberValidator = new NumberValidator(); //don't need you
        RequiredFieldValidator fieldValidate = new RequiredFieldValidator();
        RequiredFieldValidator passValidate = new RequiredFieldValidator();
        email.getValidators().add(fieldValidate);
        pass.getValidators().add(passValidate);
//        numberValidator.setMessage("Please enter a number 1-9");
//        fieldValidate.setMessage(numberValidator.getMessage());
        fieldValidate.setMessage("Please enter a valid email to login");
        passValidate.setMessage("Please enter your password");
        email.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            wrongData.setVisible(false); // hide the wrong information text when the user focuses on one of the text fields again
            if (!newValue) {
                email.validate();
            }
        });
        pass.focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            wrongData.setVisible(false); // hide the wrong information text when the user focuses on one of the text fields again
            if (!newValue) {
                pass.validate();
            }
        });
    }


}
