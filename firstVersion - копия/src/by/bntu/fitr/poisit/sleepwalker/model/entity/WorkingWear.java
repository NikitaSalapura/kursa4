package by.bntu.fitr.poisit.sleepwalker.model.entity;

import by.bntu.fitr.poisit.sleepwalker.model.exception.InvalidValueException;


public class WorkingWear extends Good {

    public static final int DEFAULT_SIZE = 5;
    public static final String DEFAULT_COLOR = "black";
    public static final String DEFAULT_BRAND = "undefined";
    public static final String DEFAULT_CATEGORY = "undefined";
    public static final String DEFAULT_MATERIAL = "undefined";

    private static final String INVALID_SIZE_MSG = "Invalid size";

    protected int size;
    protected String color;
    protected String brand;
    protected String category;
    protected String material;

    public WorkingWear() {
        initDefault();
    }

    public WorkingWear(double price) {
        super(price);
    }

    public WorkingWear(int size, String color,
                       String brand, String category, String material) {
        super.initDefault();
        initFields(size, color, brand, category, material);
    }

    public WorkingWear(double price, int size, String color,
                       String brand, String category, String material) {
        super(price);
        initFields(size, color, brand, category, material);
    }

    public WorkingWear(WorkingWear workingWear) {
        if (checkForNonNull(workingWear)) {
            copyWorkingWear(workingWear);
        } else {
            initDefault();
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) throws InvalidValueException {
        checkSizeWithException(size);
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    private boolean checkSize(int size) {
        return size >= 20;
    }

    private void checkSizeWithException(int size) throws InvalidValueException {
        if (!checkSize(size)) {
            throw new InvalidValueException(INVALID_SIZE_MSG);
        }
    }

    private boolean checkForNonNull(WorkingWear workingWear) {
        return workingWear != null;
    }

    protected void copyWorkingWear(WorkingWear workingWear) {
        super.copyGood(workingWear);
        this.size = workingWear.size;
        this.color = workingWear.color;
        this.brand = workingWear.brand;
        this.category = workingWear.category;
        this.material = workingWear.material;
    }

    protected void initFields(int size, String color,
                              String brand, String category, String material) {
        this.size = checkSize(size) ? size : DEFAULT_SIZE;
        this.color = color;
        this.brand = brand;
        this.category = category;
        this.material = material;
    }


    protected void initDefault() {
        super.initDefault();
        size = DEFAULT_SIZE;
        color = DEFAULT_COLOR;
        brand = DEFAULT_BRAND;
        category = DEFAULT_CATEGORY;
        material = DEFAULT_MATERIAL;
    }

    @Override
    public String toString() {
        return super.toString() +
                "size=" + size +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + "\', ";
    }
}
