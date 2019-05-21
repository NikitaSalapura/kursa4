package by.bntu.fitr.poisit.sleepwalker.controller;

import by.bntu.fitr.poisit.sleepwalker.model.entity.*;
import by.bntu.fitr.poisit.sleepwalker.util.JsonWorker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Main extends Application {

    private final static String PATH_TO_MAIN_WINDOW
            = "/by/bntu/fitr/poisit/sleepwalker/view/fxml/mainWindow.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(PATH_TO_MAIN_WINDOW));
        primaryStage.setTitle("Catalog");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
