package by.bntu.fitr.poisit.sleepwalker.controller;

import by.bntu.fitr.poisit.sleepwalker.model.entity.*;
import by.bntu.fitr.poisit.sleepwalker.model.exception.InvalidValueException;
import com.sun.deploy.util.StringUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Objects;

import static by.bntu.fitr.poisit.sleepwalker.util.FormHelper.showMessage;

public class GoodCreator {
    final String SUCCESS_MSG = "Done successful!";
    final String NOT_DOUBLE_FIELD_MSG = "Price must be digital. Check, please";

    static boolean isChanged;
    private static final Logger LOG;

    static {
        LOG = Logger.getLogger(GoodCreator.class);
    }

    @FXML
    private ComboBox<String> comboType;

    @FXML
    private TextField priceField, colorField, brandField,
            materialField, categoryField, uniqueField;

    public void clickOnComboType(ActionEvent actionEvent) {
        clearFields();
        if (Catalog.typeList.get(0).equals(comboType.getValue())) {
            setPromptText(uniqueField, "Equipment");
        } else if (Catalog.typeList.get(1).equals(comboType.getValue())) {
            setPromptText(uniqueField, "Sole");
        } else {
            setPromptText(uniqueField, "Name");
        }
        LOG.trace("uniqueField promptText was filled");
    }

    @FXML
    void clickOnAdd(ActionEvent event) {
        boolean isValid;
        if (Catalog.typeList.get(0).equals(comboType.getValue())) {
            Suit suit = new Suit();
            if (!checkForEmpty(uniqueField.getText())) {
                suit.setEquipment(uniqueField.getText());
            }
            isValid = setCommonFields(suit);
            if (isValid) {
                Catalog.goodContainer.getSuitList().add(suit);
            }
        } else if (Catalog.typeList.get(1).equals(comboType.getValue())) {
            Footwear footwear = new Footwear();
            if (!checkForEmpty(uniqueField.getText())) {
                footwear.setSole(uniqueField.getText());
            }
            isValid = setCommonFields(footwear);
            if (isValid) {
                Catalog.goodContainer.getFootwearList().add(footwear);
            }
        } else {
            ProtectionMean protectionMean = new ProtectionMean();
            if (!checkForEmpty(uniqueField.getText())) {
                protectionMean.setName(uniqueField.getText());
            }
            isValid = setCommonFields(protectionMean);
            if (isValid) {
                Catalog.goodContainer.getProtectionMeanList().add(protectionMean);
            }
        }

        if (isValid) {
            try {
                Catalog.goodContainer.saveInJson();
                LOG.debug("catalogItems.json was overwritten");
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
        setPromptText(uniqueField, "Equipment");
    }

    private boolean setCommonFields(WorkingWear workingWear) {
        boolean isValid = true;
        try {
            if (!checkForEmpty(priceField.getText())) {
                if (checkForDouble(priceField.getText())) {
                    workingWear.setPrice(Double.valueOf(priceField.getText()));
                } else {
                    isValid = false;
                    showMessage(NOT_DOUBLE_FIELD_MSG, Alert.AlertType.WARNING);
                }
            }
        } catch (InvalidValueException e) {
            isValid = false;
            showMessage(e.getMessage(), Alert.AlertType.WARNING);
        }
        if (isValid) {
            if (!checkForEmpty(colorField.getText())) {
                workingWear.setColor(colorField.getText());
            }
            if (!checkForEmpty(brandField.getText())) {
                workingWear.setBrand(brandField.getText());
            }
            if (!checkForEmpty(categoryField.getText())) {
                workingWear.setCategory(categoryField.getText());
            }
            if (!checkForEmpty(materialField.getText())) {
                workingWear.setMaterial(materialField.getText());
            }
        }
        return isValid;
    }

    private void setPromptText(TextField textField, String text) {
        textField.setPromptText(text);
    }

    private boolean checkForEmpty(String value) {
        return value.trim().equals("");
    }

    private boolean checkForDouble(String value) {
        return value.trim().matches("\\d*.\\d*");
    }

    private void clearFields(){
     priceField.clear();
     colorField.clear();
     brandField.clear();
     categoryField.clear();
     materialField.clear();
     uniqueField.clear();
    }
}
