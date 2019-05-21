package by.bntu.fitr.poisit.sleepwalker.model.entity;

import by.bntu.fitr.poisit.sleepwalker.model.exception.InvalidValueException;


public class Good {

    public static final double DEFAULT_PRICE = 1;

    private static final String INVALID_PRICE_MSG = "Invalid price";

    protected double price;

    public Good() {
        initDefault();
    }

    public Good(double price) {
        this.price = checkPrice(price) ? price : DEFAULT_PRICE;
    }

    public Good(Good good) {
        if (checkForNonNull(good)) {
            copyGood(good);
        } else {
            initDefault();
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws InvalidValueException {
        checkPriceWithException(price);
        this.price = price;
    }

    private boolean checkPrice(double price) {
        return price > 0;
    }

    private void checkPriceWithException(double price) throws InvalidValueException {
        if (!checkPrice(price)) {
            throw new InvalidValueException(INVALID_PRICE_MSG);
        }
    }

    private boolean checkForNonNull(Good good) {
        return good != null;
    }

    protected void copyGood(Good good) {
        price = good.price;
    }

    protected void initDefault() {
        price = DEFAULT_PRICE;
    }

    @Override
    public String toString() {
        return "price=" + price + ", ";
    }
}
