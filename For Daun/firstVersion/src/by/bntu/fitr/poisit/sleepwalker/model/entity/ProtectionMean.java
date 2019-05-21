package by.bntu.fitr.poisit.sleepwalker.model.entity;

import javafx.beans.property.SimpleStringProperty;

public class ProtectionMean extends WorkingWear {
    public static final String DEFAULT_NAME = "helmet";

    protected SimpleStringProperty name;

    public ProtectionMean() {
        initDefault();
    }

    public ProtectionMean(double price) {
        super(price);
        initFields(DEFAULT_NAME);
    }

    public ProtectionMean(String name) {
        super.initDefault();
        initFields(name);
    }

    public ProtectionMean(String color,
                    String brand, String category, String material, String name) {
        super(color, brand, category, material);
        initFields(name);
    }

    public ProtectionMean(double price, String color,
                    String brand, String category, String material, String name) {
        super(price, color, brand, category, material);
        initFields(name);
    }

    public ProtectionMean(ProtectionMean protectionMean) {
        if (checkForNonNull(protectionMean)) {
            copyFields(protectionMean);
        } else {
            initDefault();
        }
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    private boolean checkForNonNull(ProtectionMean protectionMean) {
        return protectionMean != null;
    }

    protected void initDefault() {
        super.initDefault();
        name = new SimpleStringProperty(DEFAULT_NAME);
    }

    private void initFields(String name) {
        this.name = new SimpleStringProperty(name);
    }


    protected void copyFields(ProtectionMean protectionMean){
        super.copyFields(protectionMean);
        initFields(String.valueOf(protectionMean.name));
    }

    @Override
    public String toString() {
        return "ProtectionMean{" +
                super.toString() +
                "name='" + name + '\'' +
                '}';
    }
}
