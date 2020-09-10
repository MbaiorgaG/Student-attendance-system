package Ui.Login;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Ui/Login/Login.fxml"));
        primaryStage.setTitle("Ui/Login");
        primaryStage.getIcons().add(new Image("Ui/resources/window.png")); // set window icon
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        displayDialog(primaryStage);

    }

    private void displayDialog(Stage primaryStage) {
        primaryStage.setOnCloseRequest(we -> {
            we.consume();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit");
            alert.setHeaderText("Are you sure You want to exit?");
            ButtonType Yes = new ButtonType("Yes");
            ButtonType No = new ButtonType("No");
            alert.getButtonTypes().setAll(Yes, No);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == Yes) {
                Platform.exit();
            }
        });
    }
}
