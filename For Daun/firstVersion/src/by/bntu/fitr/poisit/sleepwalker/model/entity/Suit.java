package by.bntu.fitr.poisit.sleepwalker.model.entity;

import by.bntu.fitr.poisit.sleepwalker.model.exception.InvalidValueException;
import javafx.beans.property.SimpleStringProperty;

public class Suit extends WorkingWear {

    public static final String DEFAULT_EQUIPMENT = "jacket, pants";

    protected SimpleStringProperty equipment;

    public Suit() {
        initDefault();
    }

    public Suit(double price) {
        super(price);
        initFields(DEFAULT_EQUIPMENT);
    }

    public Suit(String equipment) {
        super.initDefault();
        initFields(equipment);
    }

    public Suit(double price, String color,
                String brand, String category, String material, String equipment) {
        super(price, color, brand, category, material);
        initFields(equipment);
    }

    public Suit(Suit suit){
        if (checkForNonNull(suit)){
            copyFields(suit);
        } else{
            initDefault();
        }
    }

    public String getEquipment() {
        return equipment.get();
    }

    public void setEquipment(String equipment) {
        this.equipment.set(equipment);
    }

    protected void initDefault() {
        super.initDefault();
        equipment = new SimpleStringProperty(DEFAULT_EQUIPMENT);
    }

    protected void initFields(String equipment) {
        this.equipment = new SimpleStringProperty(equipment);
    }

    protected void copyFields(Suit suit){
        super.copyFields(suit);
        initFields(String.valueOf(suit.equipment));
    }

    @Override
    public String toString() {
        return "Suit{" +
                super.toString() +
                "equipment=" + equipment +
                '}';
    }
}

