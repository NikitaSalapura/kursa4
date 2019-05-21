package by.bntu.fitr.poisit.sleepwalker.controller;

import by.bntu.fitr.poisit.sleepwalker.model.entity.*;
import by.bntu.fitr.poisit.sleepwalker.model.exception.InvalidValueException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

import static by.bntu.fitr.poisit.sleepwalker.util.FormHelper.showMessage;

public class GoodCreator {
    final String SUCCESS_MSG = "Done successful!";

    static boolean isChanged;

    private String value;


    @FXML
    private ComboBox<String> comboType;

    @FXML
    private TextField priceField, colorField, brandField,
            materialField, categoryField, uniqField;

    public void clickOnComboType(ActionEvent actionEvent) {
        setPromptText(uniqField, comboType.getValue());
    }

    @FXML
    void clickOnAdd(ActionEvent event) {
        boolean isValid;
        if (Catalog.typeList.get(0).equals(comboType.getValue())) {
            Suit suit = new Suit();
            suit.setEquipment(uniqField.getText());
            isValid = setCommonFields(suit);
            if (isValid) {
                Catalog.goodContainer.getSuitList().add(suit);
            }
        } else if (Catalog.typeList.get(1).equals(comboType.getValue())) {
            Footwear footwear = new Footwear();
            footwear.setSole(uniqField.getText());
            isValid = setCommonFields(footwear);
            if (isValid) {
                Catalog.goodContainer.getFootwearList().add(footwear);
            }
        } else {
            ProtectionMean protectionMean = new ProtectionMean();
            protectionMean.setName(uniqField.getText());
            isValid = setCommonFields(protectionMean);
            if (isValid) {
                Catalog.goodContainer.getProtectionMeanList().add(protectionMean);
            }
        }

        if (isValid) {
            try {
                Catalog.goodContainer.saveInJson();
                isChanged = true;
                showMessage(SUCCESS_MSG, Alert.AlertType.INFORMATION);
            } catch (IOException e) {
                showMessage(Catalog.FILE_NOT_FOUND_MESSAGE, Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void initialize() {
        comboType.setItems(Catalog.typeList);
        comboType.setValue(Catalog.typeList.get(0));
        isChanged = false;
        setPromptText(uniqField, comboType.getValue());
    }

    private boolean setCommonFields(WorkingWear workingWear) {
        boolean isValid = true;
        try {
            workingWear.setPrice(Double.valueOf(priceField.getText()));
        } catch (InvalidValueException | NumberFormatException e) {
            isValid = false;
            showMessage(e.getMessage(), Alert.AlertType.WARNING);
        }
        if (isValid) {
            workingWear.setColor(colorField.getText());
            workingWear.setBrand(brandField.getText());
            workingWear.setCategory(categoryField.getText());
            workingWear.setMaterial(materialField.getText());
        }
        return isValid;
    }

    private void setPromptText(TextField textField, String text) {
        textField.setPromptText(text);
    }

}
