package by.bntu.fitr.poisit.sleepwalker.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import by.bntu.fitr.poisit.sleepwalker.model.entity.*;
import by.bntu.fitr.poisit.sleepwalker.util.JsonWorker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Catalog {

    public static boolean isAdmin;

    private ObservableList<Suit> suits;
    private ObservableList<Footwear> footwears;
    private ObservableList<ProtectionMean> protectionMeans;
    private ObservableList<Good> goods;

    private final ObservableList<String> typeList = FXCollections
            .observableArrayList("Suit", "Footwear", "Protection mean");

    private final String[] commonFields = new String[]
            {"Price", "Color", "Brand", "Category", "Material"};

    private GoodContainer goodContainer;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView signInImage;

    @FXML
    private ImageView signOutImage;

    @FXML
    private Label logInLabel;

    @FXML
    private Label logOutLabel;

    @FXML
    private Button removeButton;

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<String> comboType;

    @FXML
    private TableView goodTable;

    private TableColumn<Good, String>
            priceColumn, colorColumn, brandColumn, categoryColumn, materialColumn,
            equipmentColumn, soleColumn, nameColumn;

//    private TableColumn<Good, Image> imageColumn;

    public void clickOnComboType(ActionEvent actionEvent) {
        if ("Suit".equals(comboType.getValue())) {
            showSuit();
        } else if ("Footwear".equals(comboType.getValue())) {
//            addColumns("Sole");
            showFootwear();
        } else if ("Protection mean".equals(comboType.getValue())) {
//            addColumns("Name");
            showProtectionMean();
        }
    }

    @FXML
    public void clickOnSignOut(MouseEvent mouseEvent) {
        isAdmin = false;
        initialize();
    }

    @FXML
    void clickOnSignIn(MouseEvent event) {
        FXMLLoader loader =
                new FXMLLoader((getClass().getResource("/by/bntu/fitr/poisit/sleepwalker/view/fxml/loginWindow.fxml")));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();

        stage.initModality(Modality.WINDOW_MODAL);
        Node source = (Node) event.getSource();
        stage.initOwner(source.getScene().getWindow());

        stage.setScene(new Scene(root));
        stage.showAndWait();
        if (Login.isAdmin) initialize();
    }

    @FXML
    void initialize() {
        if (isAdmin) {
            setAdminMode();
        } else {
            setGeneralMode();
        }
        comboType.setItems(typeList);
        initCommonColumns();
    }

    private void initCommonColumns() {
        try {
            goodContainer = JsonWorker.readToGoodContainer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        priceColumn.setCellValueFactory(cellData ->
//                cellData.getValue().getPrice());
        priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        colorColumn = new TableColumn<>("Color");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("Color"));

        brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(new PropertyValueFactory<>("brand"));

        categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        materialColumn = new TableColumn<>("Material");
        materialColumn.setCellValueFactory(new PropertyValueFactory<>("material"));
        goodTable.getColumns().addAll
                (priceColumn, colorColumn, brandColumn, categoryColumn, materialColumn);

        showSuit();

//        for (String column : commonFields) {
//            goodTable.getColumns().add(new TableColumn<Good, String>(column));
//        }
    }

    private void showSuit() {
        goodTable.getColumns().remove
                (commonFields.length, goodTable.getColumns().size());
        equipmentColumn = new TableColumn<>("Equipment");
        equipmentColumn.setCellValueFactory(new PropertyValueFactory<>("equipment"));
        goodTable.getColumns().add(equipmentColumn);
        suits = FXCollections.observableList(goodContainer.getSuitList());
        goodTable.setItems(suits);
    }

    private void showFootwear() {
        goodTable.getColumns().remove
                (commonFields.length, goodTable.getColumns().size());
        soleColumn = new TableColumn<>("Sole");
        soleColumn.setCellValueFactory(new PropertyValueFactory<>("sole"));
        goodTable.getColumns().add(soleColumn);
        footwears = FXCollections.observableList(goodContainer.getFootwearList());
        goodTable.setItems(footwears);
    }

    private void showProtectionMean() {
        goodTable.getColumns().remove
                (commonFields.length, goodTable.getColumns().size());
        nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        goodTable.getColumns().add(nameColumn);
        protectionMeans = FXCollections.observableList(goodContainer.getProtectionMeanList());
        goodTable.setItems(protectionMeans);
    }

    private void addColumns(String... columnList) {
        goodTable.getColumns().remove
                (commonFields.length, goodTable.getColumns().size());
        for (String column : columnList) {
            TableColumn tableColumn = new TableColumn(column);
//            tableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            goodTable.getColumns().add(tableColumn);
        }
    }

    private void setAdminMode() {
        signInImage.setVisible(false);
        logInLabel.setVisible(false);
        signOutImage.setVisible(true);
        logOutLabel.setVisible(true);
        addButton.setVisible(true);
        removeButton.setVisible(true);
    }

    private void setGeneralMode() {
        signInImage.setVisible(true);
        logInLabel.setVisible(true);
        signOutImage.setVisible(false);
        logOutLabel.setVisible(false);
        addButton.setVisible(false);
        removeButton.setVisible(false);
    }

}
