package by.bntu.fitr.poisit.sleepwalker.controller;

import by.bntu.fitr.poisit.sleepwalker.model.entity.LoginData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static by.bntu.fitr.poisit.sleepwalker.util.FormHelper.showMessage;

public class LogChanger {

    static final String EMPTY_LOGIN_MSG = "Empty login. Check, please";
    static final String EMPTY_PASSWORD_MSG = "Empty password. Check, please";
    static final String WRONG_PREVIOUS_MSG = "Wrong previous password. Check, please";
    static final String SUCCESS_LOG_MSG = "Login changed successful";
    static final String SUCCESS_PASSWORD_MSG = "Password changed successful";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField, passwordField;

    @FXML
    private PasswordField previousPasswordField;


    public void clickOnChangeLogin(ActionEvent actionEvent) {
        if (checkPreviousPassword()) {
            if (!loginField.getText().isEmpty()) {
                try {
                    LoginData.getInstance().setLogin(loginField.getText());
                    showMessage(SUCCESS_LOG_MSG, Alert.AlertType.INFORMATION);
                } catch (IOException e) {
                    showMessage(Catalog.FILE_NOT_FOUND_MESSAGE, Alert.AlertType.ERROR);
                }
            } else {
                showMessage(EMPTY_LOGIN_MSG, Alert.AlertType.WARNING);
            }
        } else {
            showMessage(WRONG_PREVIOUS_MSG, Alert.AlertType.WARNING);
        }
    }

    public void clickOnChangePassword(ActionEvent actionEvent) {
        if (checkPreviousPassword()) {
            if (!passwordField.getText().isEmpty()) {
                try {
                    LoginData.getInstance().setPassword(passwordField.getText());
                    showMessage(SUCCESS_PASSWORD_MSG, Alert.AlertType.INFORMATION);
                } catch (IOException e) {
                    showMessage(Catalog.FILE_NOT_FOUND_MESSAGE, Alert.AlertType.ERROR);
                }
            } else {
                showMessage(EMPTY_LOGIN_MSG, Alert.AlertType.WARNING);
            }
        } else {
            showMessage(WRONG_PREVIOUS_MSG, Alert.AlertType.WARNING);
        }
    }

    private boolean checkPreviousPassword() {
        boolean isSame;
        try {
            isSame = previousPasswordField.getText().equals
                    (LoginData.getInstance().getPassword());
        } catch (FileNotFoundException e) {
            showMessage(Catalog.FILE_NOT_FOUND_MESSAGE, Alert.AlertType.ERROR);
            isSame = false;
        }
        return isSame;
    }

    @FXML
    void initialize() {
    }
}
