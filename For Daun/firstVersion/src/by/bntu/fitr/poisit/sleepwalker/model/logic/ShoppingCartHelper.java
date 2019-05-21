package by.bntu.fitr.poisit.sleepwalker.model.logic;

import by.bntu.fitr.poisit.sleepwalker.model.entity.Good;
import by.bntu.fitr.poisit.sleepwalker.model.entity.ShoppingCart;
import by.bntu.fitr.poisit.sleepwalker.model.exception.ArrayDimensionException;
import by.bntu.fitr.poisit.sleepwalker.model.exception.NullValueException;


public class ShoppingCartHelper {

    private static final String NULL_EXCEPTION_MSG = "Null is not allowed!";

    private static void checkForNull(ShoppingCart shoppingCart)
            throws NullValueException {
        if (shoppingCart == null) {
            throw new NullValueException(NULL_EXCEPTION_MSG);
        }
    }

    public static double getTotalPrice(ShoppingCart shoppingCart)
            throws ArrayDimensionException, NullValueException {
        checkForNull(shoppingCart);
        double totalPrice = 0;
        for (int i = 0; i < shoppingCart.getNumberOfGoods(); i++) {
            totalPrice += shoppingCart.getElement(i).getPrice();
        }
        return totalPrice;
    }

    public static double getMaxPrice(ShoppingCart shoppingCart)
            throws ArrayDimensionException, NullValueException {
        checkForNull(shoppingCart);
        double maxPrice = shoppingCart.getElement(0).getPrice();
        for (int i = 0; i < shoppingCart.getNumberOfGoods(); i++) {
            if (maxPrice < shoppingCart.getElement(i).getPrice()) {
                maxPrice = shoppingCart.getElement(i).getPrice();
            }
        }
        return maxPrice;
    }

    public static double getMinPrice(ShoppingCart shoppingCart)
            throws ArrayDimensionException, NullValueException {
        checkForNull(shoppingCart);
        double minPrice = shoppingCart.getElement(0).getPrice();
        for (int i = 0; i < shoppingCart.getNumberOfGoods(); i++) {
            if (minPrice > shoppingCart.getElement(i).getPrice()) {
                minPrice = shoppingCart.getElement(i).getPrice();
            }
        }
        return minPrice;
    }

    public static Good getRichestGood
            (ShoppingCart shoppingCart)
            throws ArrayDimensionException, NullValueException {
        checkForNull(shoppingCart);
        double maxPrice = shoppingCart.getElement(0).getPrice();
        int indexOfRichest = 0;
        for (int i = 0; i < shoppingCart.getNumberOfGoods(); i++) {
            if (maxPrice < shoppingCart.getElement(i).getPrice()) {
                maxPrice = shoppingCart.getElement(i).getPrice();
                indexOfRichest = i;
            }
        }
        return shoppingCart.getElement(indexOfRichest);
    }

}
