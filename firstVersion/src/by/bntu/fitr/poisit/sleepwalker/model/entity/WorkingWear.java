package by.bntu.fitr.poisit.sleepwalker.model.entity;

import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;


public class WorkingWear extends Good {

    public static final String DEFAULT_COLOR = "undefined";
    public static final String DEFAULT_BRAND = "undefined";
    public static final String DEFAULT_CATEGORY = "undefined";
    public static final String DEFAULT_MATERIAL = "undefined";

    protected SimpleStringProperty color;
    protected SimpleStringProperty brand;
    protected SimpleStringProperty category;
    protected SimpleStringProperty material;

    public WorkingWear() {
        initDefault();
    }

    public WorkingWear(double price) {
        super(price);
    }

    public WorkingWear(String color,
                       String brand, String category, String material) {
        super.initDefault();
        initFields(color, brand, category, material);
    }

    public WorkingWear(double price, String color,
                       String brand, String category, String material) {
        super(price);
        initFields(color, brand, category, material);
    }

    public WorkingWear(WorkingWear workingWear) {
        if (checkForNonNull(workingWear)) {
            copyFields(workingWear);
        } else {
            initDefault();
        }
    }

    public String getColor() {
        return color.get();
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    public String getBrand() {
        return brand.get();
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getMaterial() {
        return material.get();
    }

    public void setMaterial(String material) {
        this.material.set(material);
    }

    private boolean checkForNonNull(WorkingWear workingWear) {
        return workingWear != null;
    }

    protected void copyFields(WorkingWear workingWear) {
        super.copyFields(workingWear);
        this.color = workingWear.color;
        this.brand = workingWear.brand;
        this.category = workingWear.category;
        this.material = workingWear.material;
    }

    protected void initFields(String color,
                              String brand, String category, String material) {
        this.color = new SimpleStringProperty(color);
        this.brand = new SimpleStringProperty(brand);
        this.category = new SimpleStringProperty(category);
        this.material = new SimpleStringProperty(material);
    }


    protected void initDefault() {
        super.initDefault();
        initFields(DEFAULT_COLOR,DEFAULT_BRAND,DEFAULT_CATEGORY,DEFAULT_MATERIAL);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WorkingWear that = (WorkingWear) o;
        return Objects.equals(color, that.color) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(category, that.category) &&
                Objects.equals(material, that.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color, brand, category, material);
    }

    @Override
    public String toString() {
        return super.toString() +
                "color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + "\', ";
    }
}
