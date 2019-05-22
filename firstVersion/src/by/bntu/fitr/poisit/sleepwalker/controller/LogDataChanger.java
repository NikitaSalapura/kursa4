package by.bntu.fitr.poisit.sleepwalker.controller;

import by.bntu.fitr.poisit.sleepwalker.model.entity.LoginData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;

import static by.bntu.fitr.poisit.sleepwalker.util.FormHelper.showMessage;

public class LogDataChanger {
    static final String EMPTY_LOGIN_MSG = "Empty login. Check, please";
    static final String EMPTY_PASSWORD_MSG = "Empty password. Check, please";
    static final String WRONG_PREVIOUS_MSG = "Wrong previous password. Check, please";
    static final String SUCCESS_LOG_MSG = "Login changed successful";
    static final String SUCCESS_PASSWORD_MSG = "Password changed successful";
    private static final Logger LOG;

    static {
        LOG = Logger.getLogger(LogDataChanger.class);
    }

    @FXML
    private PasswordField previousPasswordField;

    @FXML
    private TextField newLoginField, newPasswordField;

    @FXML
    void clickOnChangeLogin(ActionEvent event) {
        if (checkPreviousPassword()) {
            if (!newLoginField.getText().isEmpty()) {
                try {
                    LoginData.getInstance().setLogin(newLoginField.getText());
                    LOG.info("Login was changed");
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

    @FXML
    void clickOnChangePassword(ActionEvent event) {
        if (checkPreviousPassword()) {
            if (!newPasswordField.getText().isEmpty()) {
                try {
                    LoginData.getInstance().setPassword(newPasswordField.getText());
                    LOG.info("Password was changed");
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
