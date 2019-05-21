package by.bntu.fitr.poisit.sleepwalker.controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import by.bntu.fitr.poisit.sleepwalker.model.entity.Good;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Catalog {

    private final ObservableList<String> typeList = FXCollections
            .observableArrayList("Костюм", "Обувь", "Средство защиты");


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView signInImage;

    @FXML
    private ComboBox comboType;

    @FXML
    private TableView goodTable;

    public void clickOnComboType(ActionEvent actionEvent) {

        if ("Костюм".equals(comboType.getValue())) {
            addColumns("Цвет", "Бренд");
        } else if ("Обувь".equals(comboType.getValue())) {
            addColumns("Подошва");
        }
    }

    @FXML
    void initialize() {
//        Good[] g = new Good[]{new Suit(), new Footwear()};
//        List<Good> goodList = new ArrayList(Arrays.asList(g));

//        comboType.getItems().addAll(Suit.class, Footwear.class);
        //comboType.setItems(typeList);

        comboType.setItems(typeList);

        signInImage.setOnMouseClicked(event -> {
            FXMLLoader loader =
                    new FXMLLoader((getClass().getResource("../view/fxml/loginWindow.fxml")));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    private void addColumns(String... columnList) {
        goodTable.getColumns().clear();
        for (String column : columnList) {
            goodTable.getColumns().add(new TableColumn<Good, String>(column));
        }
    }
}
