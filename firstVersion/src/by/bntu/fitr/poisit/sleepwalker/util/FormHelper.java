package by.bntu.fitr.poisit.sleepwalker.util;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FormHelper {
    public static boolean isSelectedItemInTable(TableView<?> tableView) {
        return tableView.getSelectionModel().getSelectedItem() != null;
    }

    public static void openWindow(String windowName, Parent root, Event event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle(windowName);
        stage.initModality(Modality.WINDOW_MODAL);
        Node source = (Node) event.getSource();
        stage.initOwner(source.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    public static void showError(String msg, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType, msg);
        alert.showAndWait();
    }
}
