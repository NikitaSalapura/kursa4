package by.bntu.fitr.poisit.sleepwalker.model.entity;

import by.bntu.fitr.poisit.sleepwalker.model.exception.InvalidValueException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;


public class Good {

    public static final double DEFAULT_PRICE = 1;
//    public static final String DEFAULT_IMAGE = "D:\\BNTU\\OOP\\kursa4\\firstVersion\\src\\by\\bntu\\fitr\\poisit\\sleepwalker\\view\\images\\noPhoto.png";

    private static final String INVALID_PRICE_MSG = "Invalid price";

    protected SimpleDoubleProperty price;
//    protected ImageView image;

    public Good() {
        initDefault();
    }

    public Good(double price) {
        this.price = new SimpleDoubleProperty(checkPrice(price) ? price : DEFAULT_PRICE);
    }

//    public Good(double price, ImageView image) {
//        this.price = new SimpleDoubleProperty(checkPrice(price) ? price : DEFAULT_PRICE);
//        this.image = image;
//    }

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
//
//    public ImageView getImage() {
//        return image;
//    }
//
//    public void setImage(ImageView image) {
//        this.image = image;
//    }

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
    public String toString() {
        return "price=" + price + ", ";
    }
}
