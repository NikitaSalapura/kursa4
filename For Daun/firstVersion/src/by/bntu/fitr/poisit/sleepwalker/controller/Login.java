package by.bntu.fitr.poisit.sleepwalker.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import by.bntu.fitr.poisit.sleepwalker.model.entity.LoginData;
import by.bntu.fitr.poisit.sleepwalker.util.Animation;
import javafx.event.ActionEvent;
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

    public static boolean isAdmin;

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

    public void clickOnSignInButton(ActionEvent actionEvent) {
        String loginText = loginField.getText().trim();
        String passwordText = passwordField.getText().trim();
        try {
            if (loginText.equals(LoginData.getInstance().getLogin())
                    && passwordText.equals(LoginData.getInstance().getPassword())) {
                Catalog.isAdmin = true;
                isAdmin = true;
                signInButton.getScene().getWindow().hide();

//                    FXMLLoader loader = new FXMLLoader();
//                    loader.setLocation(getClass().getResource("../view/fxml/catalogEditor.fxml"));
//                    try {
//                        loader.load();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Parent root = loader.getRoot();
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(root));
//                    stage.showAndWait();
            } else {
                wrongInputLabel.setText("Wrong login or password");
                Animation.shake(loginField);
                Animation.shake(passwordField);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        isAdmin = false;
    }
}
