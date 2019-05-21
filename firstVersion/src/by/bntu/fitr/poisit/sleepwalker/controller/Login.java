package by.bntu.fitr.poisit.sleepwalker.controller;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import static by.bntu.fitr.poisit.sleepwalker.util.FormHelper.*;

import by.bntu.fitr.poisit.sleepwalker.model.entity.LoginData;
import by.bntu.fitr.poisit.sleepwalker.util.Animation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Login {
    public static final String WRONG_LOGIN_MSG = "Wrong login or password";

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
            } else {
                wrongInputLabel.setText(WRONG_LOGIN_MSG);
                Animation.shake(loginField);
                Animation.shake(passwordField);
            }
        } catch (FileNotFoundException e) {
            showMessage(Catalog.FILE_NOT_FOUND_MESSAGE, Alert.AlertType.ERROR);
        }
    }

    @FXML
    void initialize() {
        isAdmin = false;
    }
}
