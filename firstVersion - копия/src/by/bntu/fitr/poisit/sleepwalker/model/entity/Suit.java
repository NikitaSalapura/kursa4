package by.bntu.fitr.poisit.sleepwalker.model.entity;

import by.bntu.fitr.poisit.sleepwalker.model.exception.InvalidValueException;

public class Suit extends WorkingWear {

    public static final int DEFAULT_HEIGHT = 170;

    private static final String INVALID_HEIGHT_MSG = "Invalid height";

    protected int height;

    public Suit() {
        initDefault();
    }

    public Suit(double price) {
        super(price);
    }

    public Suit(int height) {
        super.initDefault();
        initFields(height);
    }

    public Suit(double price, int size, String color,
                String brand, String category, String material, int height) {
        super(price, size, color, brand, category, material);
        initFields(height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws InvalidValueException {
        checkHeightWithException(height);
        this.height = height;
    }

    private boolean checkHeight(int height) {
        return height >= 120;
    }

    private void checkHeightWithException(int height) throws InvalidValueException {
        if (!checkHeight(height)) {
            throw new InvalidValueException(INVALID_HEIGHT_MSG);
        }
    }

    protected void initDefault() {
        super.initDefault();
        height = DEFAULT_HEIGHT;
    }

    protected void initFields(int height) {
        this.height = checkHeight(height) ? height : DEFAULT_HEIGHT;
    }


    @Override
    public String toString() {
        return "Suit{" +
                super.toString() +
                "height=" + height +
                '}';
    }
}

