package by.bntu.fitr.poisit.sleepwalker.controller;

import by.bntu.fitr.poisit.sleepwalker.model.entity.*;
import by.bntu.fitr.poisit.sleepwalker.util.FormHelper;
import by.bntu.fitr.poisit.sleepwalker.util.JsonWorker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Main extends Application {

    private static final String PATH_TO_MAIN_WINDOW
            = "/by/bntu/fitr/poisit/sleepwalker/view/fxml/mainWindow.fxml";
    private static final Logger LOG;

    static {
        LOG = Logger.getLogger(Main.class);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(PATH_TO_MAIN_WINDOW));
            primaryStage.setTitle("Catalog");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            FormHelper.showMessage(e.getMessage(), Alert.AlertType.ERROR);
            LOG.fatal("mainWindow.fxml was not found");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
