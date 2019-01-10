package com.keitam.kayak.util;

import com.keitam.kayak.model.CustomerCart;
import javafx.collections.ObservableList;

public class KayakUtil {

    public static double getDiscountSaving(ObservableList<CustomerCart> carts) {
        double totalPrice = 0;
        for (CustomerCart item : carts) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        if (totalPrice > 500)
            return format((totalPrice * .10));
        else if (totalPrice > 200)
            return format((totalPrice * .05));
        return 0;
    }

    public static double getTotalPrice(ObservableList<CustomerCart> carts) {
        double totalPrice = 0;
        for (CustomerCart item : carts) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return format((totalPrice - getDiscountSaving(carts)));
    }

    private static double format(double price){
        return Math.round(price * 100.0) / 100.0;
    }
}
