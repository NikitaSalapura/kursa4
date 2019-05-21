package by.bntu.fitr.poisit.sleepwalker.model.entity;


import by.bntu.fitr.poisit.sleepwalker.model.exception.ArrayDimensionException;
import by.bntu.fitr.poisit.sleepwalker.model.exception.NullValueException;

import java.util.Arrays;

public class ShoppingCart {
    public static final int DEFAULT_SIZE = 0;

    private static final String DIMENSION_EXCEPTION_MSG = "Wrong listOfDecorations dimension!";
    private static final String NULL_EXCEPTION_MSG = "Null listOfDecorations is not allowed!";

    private Good[] listOfGoods;

    public ShoppingCart() {
        listOfGoods = new Good[DEFAULT_SIZE];
    }

    public ShoppingCart(Good... listOfGoods) throws NullValueException {
        checkForNonNull(listOfGoods);
        initArray(listOfGoods);
    }

    public ShoppingCart(ShoppingCart shoppingCart) throws NullValueException {
        this(shoppingCart.listOfGoods);
    }

    public int getNumberOfGoods() {
        return listOfGoods.length;
    }

    public Good getElement(int index) throws ArrayDimensionException {
        checkRange(index);
        return listOfGoods[index];
    }

    public void changeElement(int index, Good decoration)
            throws ArrayDimensionException {
        checkRange(index);
        listOfGoods[index] = decoration;
    }

    private String outOfBoundsMsg(int index) {
        return DIMENSION_EXCEPTION_MSG + "\ni = " + index
                + "\nSize: [" + getNumberOfGoods() + "]";
    }

    private void checkRange(int index) throws ArrayDimensionException {
        if (index < 0 || index >= getNumberOfGoods()) {
            throw new ArrayDimensionException(outOfBoundsMsg(index));
        }
    }

    private void checkForNonNull(Good[] array) throws NullValueException {
        if (array == null) {
            throw new NullValueException(NULL_EXCEPTION_MSG);
        }
    }

    private void add(Good... array) {
        listOfGoods = Arrays.copyOf(listOfGoods,
                getNumberOfGoods() + array.length);
        System.arraycopy(array, 0, listOfGoods,
                getNumberOfGoods() - array.length, array.length);
    }

    private void initArray(Good... listOfGoods) {
        this.listOfGoods = Arrays.copyOf(listOfGoods, listOfGoods.length);
        System.arraycopy(listOfGoods, 0,
                this.listOfGoods, 0, listOfGoods.length);
    }

    public void addGooods
            (Good... array) throws NullValueException {
        checkForNonNull(array);
        add(array);
    }

    public void removeGood(int index) throws ArrayDimensionException {
        checkRange(index);

        Good[] newArray = new Good[getNumberOfGoods() - 1];
        System.arraycopy(listOfGoods, 0, newArray, 0, index);
        System.arraycopy
                (listOfGoods, index + 1,
                        newArray, index, getNumberOfGoods() - index - 1);
        listOfGoods = new Good[newArray.length];
        add(newArray);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Good good : listOfGoods) {
            builder.append(good.toString()).append("\n");
        }
        return builder.toString();
    }
}