package by.bntu.fitr.poisit.sleepwalker.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import by.bntu.fitr.poisit.sleepwalker.util.Animation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Login {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signInButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label wrongInputLabel;

    @FXML
    void initialize() {
        signInButton.setOnAction(event -> {
            String loginText = loginField.getText().trim();
            String passwordText = passwordField.getText().trim();
            if (!loginText.isEmpty() && !passwordText.isEmpty()) {
                signInButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/catalogEditor.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } else {
                wrongInputLabel.setText("Неверный логин или пароль");
                Animation.shake(loginField);
                Animation.shake(passwordField);
            }
        });
    }
}
