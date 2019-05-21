package by.bntu.fitr.poisit.sleepwalker.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static by.bntu.fitr.poisit.sleepwalker.util.FormHelper.*;

import by.bntu.fitr.poisit.sleepwalker.model.entity.*;
import by.bntu.fitr.poisit.sleepwalker.util.JsonWorker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Catalog {

    static final String FILE_NOT_FOUND_MESSAGE
            = "Fxml file not found";

    static final String NOT_SELECTED_GOOD_MSG
            = "A good is not selected";

    static final String PATH_TO_LOGIN_WINDOW
            = "/by/bntu/fitr/poisit/sleepwalker/view/fxml/loginWindow.fxml";

    static final String PATH_TO_LOG_CHANGER_WINDOW
            = "/by/bntu/fitr/poisit/sleepwalker/view/fxml/logChangerWindow.fxml";

    static final String PATH_TO_GOOD_CREATOR_WINDOW
            = "/by/bntu/fitr/poisit/sleepwalker/view/fxml/goodCreatorWindow.fxml";

    final static ObservableList<String> typeList = FXCollections
            .observableArrayList("Suit", "Footwear", "Protection mean");

    final static String[] commonFields = new String[]
            {"Price", "Color", "Brand", "Category", "Material"};

    public static boolean isAdmin = true;

    static GoodContainer goodContainer;

    private ObservableList<Suit> suits;
    private ObservableList<Footwear> footwears;
    private ObservableList<ProtectionMean> protectionMeans;
    private ObservableList<Good> goods;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView signInImage, signOutImage;

    @FXML
    private Label logInLabel, logOutLabel;

    @FXML
    private Button removeButton, addButton, changeLogButton;

    @FXML
    private ComboBox<String> comboType;

    @FXML
    private TableView goodTable;

    private TableColumn<Good, String>
            priceColumn, colorColumn, brandColumn, categoryColumn, materialColumn,
            equipmentColumn, soleColumn, nameColumn;

//    private TableColumn<Good, Image> imageColumn;

    public void clickOnComboType(ActionEvent actionEvent) {
        if (typeList.get(0).equals(comboType.getValue())) {
            showSuit();
        } else if (typeList.get(1).equals(comboType.getValue())) {
            showFootwear();
        } else {
            showProtectionMean();
        }
    }

    @FXML
    public void clickOnSignOut(MouseEvent mouseEvent) {
        isAdmin = false;
        initialize();
    }

    @FXML
    public void clickOnAdd(ActionEvent actionEvent) {
        FXMLLoader loader =
                new FXMLLoader((getClass().getResource(PATH_TO_GOOD_CREATOR_WINDOW)));
        try {
            Parent root = loader.load();
            openWindow("GoodCreator", root, actionEvent);
        } catch (IOException e) {
            showMessage(FILE_NOT_FOUND_MESSAGE, Alert.AlertType.ERROR);
        }

        if(GoodCreator.isChanged) initialize();
    }

    public void clickOnChangeLog(ActionEvent actionEvent) {
        FXMLLoader loader =
                new FXMLLoader((getClass().getResource(PATH_TO_LOG_CHANGER_WINDOW)));
        try {
            Parent root = loader.load();
            openWindow("Log data changer", root, actionEvent);
        } catch (IOException e) {
            showMessage(FILE_NOT_FOUND_MESSAGE, Alert.AlertType.ERROR);
        }
    }

    @FXML
    void clickOnSignIn(MouseEvent event) {
        FXMLLoader loader =
                new FXMLLoader((getClass().getResource(PATH_TO_LOGIN_WINDOW)));
        try {
            Parent root = loader.load();
            openWindow("Authorization", root, event);
        } catch (IOException e) {
            showMessage(FILE_NOT_FOUND_MESSAGE, Alert.AlertType.ERROR);
        }
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
        comboType.setValue(typeList.get(0));
        initCommonColumns();

        suits = FXCollections.observableList(goodContainer.getSuitList());
        footwears = FXCollections.observableList(goodContainer.getFootwearList());
        protectionMeans = FXCollections.observableList(goodContainer.getProtectionMeanList());
        showSuit();
    }

    private void initCommonColumns() {
        try {
            goodContainer = JsonWorker.readToGoodContainer
                    (GoodContainer.PATH_TO_FILE_OF_GOOD_CONTAINER);
        } catch (FileNotFoundException e) {
            showMessage(FILE_NOT_FOUND_MESSAGE, Alert.AlertType.ERROR);
        }
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
        goodTable.setItems(suits);
    }

    private void showFootwear() {
        goodTable.getColumns().remove
                (commonFields.length, goodTable.getColumns().size());
        soleColumn = new TableColumn<>("Sole");
        soleColumn.setCellValueFactory(new PropertyValueFactory<>("sole"));
        goodTable.getColumns().add(soleColumn);
        goodTable.setItems(footwears);
    }

    private void showProtectionMean() {
        goodTable.getColumns().remove
                (commonFields.length, goodTable.getColumns().size());
        nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        goodTable.getColumns().add(nameColumn);
        goodTable.setItems(protectionMeans);
    }

    private void addColumns(String... columnList) {
        goodTable.getColumns().remove
                (commonFields.length, goodTable.getColumns().size());
        for (String column : columnList) {
            TableColumn tableColumn = new TableColumn(column);
            goodTable.getColumns().add(tableColumn);
        }
    }

    private void setAdminMode() {
        signInImage.setVisible(false);
        logInLabel.setVisible(false);
        changeLogButton.setVisible(true);
        signOutImage.setVisible(true);
        logOutLabel.setVisible(true);
        addButton.setVisible(true);
        removeButton.setVisible(true);
    }

    private void setGeneralMode() {
        signInImage.setVisible(true);
        logInLabel.setVisible(true);
        changeLogButton.setVisible(false);
        signOutImage.setVisible(false);
        logOutLabel.setVisible(false);
        addButton.setVisible(false);
        removeButton.setVisible(false);
    }

    public void clickOnRemoveButton(ActionEvent actionEvent) {
        if (!isSelectedItemInTable(goodTable)) {
            showMessage(NOT_SELECTED_GOOD_MSG, Alert.AlertType.INFORMATION);
        } else {
            if ("Suit".equals(comboType.getValue())) {

                goodContainer.getSuitList()
                        .remove((goodTable.getSelectionModel().getSelectedItem()));
                suits.remove(goodTable.getSelectionModel().getSelectedItem());
                showSuit();
            } else if ("Footwear".equals(comboType.getValue())) {
                goodContainer.getFootwearList()
                        .remove((goodTable.getSelectionModel().getSelectedItem()));
                footwears.remove(goodTable.getSelectionModel().getSelectedItem());
                showFootwear();
            } else {
                goodContainer.getProtectionMeanList()
                        .remove((goodTable.getSelectionModel().getSelectedItem()));
                protectionMeans.remove(goodTable.getSelectionModel().getSelectedItem());
                showProtectionMean();
            }
            try {
                goodContainer.saveInJson();
            } catch (IOException e) {
                showMessage(FILE_NOT_FOUND_MESSAGE, Alert.AlertType.ERROR);
            }
        }
    }
}
