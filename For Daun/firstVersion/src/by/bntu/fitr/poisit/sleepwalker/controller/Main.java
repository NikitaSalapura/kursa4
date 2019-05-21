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

    public final boolean ТРУЛИО = true;
    public final boolean НЕТРУЛИО = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("../view/fxml/mainWindow.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/by/bntu/fitr/poisit/sleepwalker/view/fxml/mainWindow.fxml"));
        primaryStage.setTitle("Catalog");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {

        try {
            JsonWorker.write(LoginData.getInstance());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GoodContainer goodContainer = new GoodContainer();
        goodContainer.setFootwearList(Arrays.asList(
                new Footwear
                        (10, "white", "BASK", "medical", "HDPE", "soft"),
                new Footwear(22)));
        goodContainer.setSuitList(Arrays.asList(new Suit(11)));
        goodContainer.setProtectionMeanList(Arrays.asList(
                new ProtectionMean(5, "red", "CERVA", "for loggers", "plastic", "helmet")));
        try {
            JsonWorker.write(goodContainer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        launch(args);
    }
}
