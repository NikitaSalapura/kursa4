package by.bntu.fitr.poisit.sleepwalker.model.entity;

import by.bntu.fitr.poisit.sleepwalker.model.exception.InvalidValueException;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Objects;

public class Good {

    public static final double DEFAULT_PRICE = 1;

    private static final String INVALID_PRICE_MSG = "Invalid price";

    protected SimpleDoubleProperty price;

    public Good() {
        initDefault();
    }

    public Good(double price) {
        this.price = new SimpleDoubleProperty(checkPrice(price) ? price : DEFAULT_PRICE);
    }


    public Good(Good good) {
        if (checkForNonNull(good)) {
            copyFields(good);
        } else {
            initDefault();
        }
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) throws InvalidValueException {
        checkPriceWithException(price);
        this.price.set(price);
    }

    private boolean checkPrice(double price) {
        return price > 0;
    }

    private void checkPriceWithException(double price) throws InvalidValueException {
        if (!checkPrice(price)) {
            throw new InvalidValueException(INVALID_PRICE_MSG);
        }
    }

    protected boolean checkForNonNull(Good good) {
        return good != null;
    }

    protected void copyFields(Good good) {
        price = good.price;
    }

    protected void initDefault() {
        price = new SimpleDoubleProperty(DEFAULT_PRICE);
//        image = new ImageView((Element) new Image(DEFAULT_IMAGE));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return Objects.equals(price, good.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }

    @Override
    public String toString() {
        return "price=" + price + ", ";
    }
}
