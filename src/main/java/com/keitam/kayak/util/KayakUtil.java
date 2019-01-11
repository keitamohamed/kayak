package com.keitam.kayak.util;

import com.keitam.kayak.model.CustomerCart;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.NumberFormat;

public class KayakUtil {
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getCurrencyInstance();

    public static double getDiscountSaving(ObservableList<CustomerCart> carts) {
        double totalPrice = 0;
        for (CustomerCart item : carts) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        if (totalPrice > 500)
            return .10;
        else if (totalPrice > 200)
            return .05;
        return 0;
    }

    public static double getTotalPrice(ObservableList<CustomerCart> carts, Label discountAmount) {
        double totalPrice = 0;
        for (CustomerCart item : carts) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        double saving = format(totalPrice * getDiscountSaving(carts));
        discountAmount.setText("Save Amount: " + NUMBER_FORMAT.format(saving));
        return format(totalPrice - saving);
    }

    public static ImageView getProductImage(String imageName, int imageWidth, int imageHeight) {
        Image image;
        ImageView view;

        try {
            image = new Image(KayakUtil.class.getResourceAsStream("/image/product_image/" + imageName + ".png"));
        }catch (NullPointerException ex) {
            image = new Image(KayakUtil.class.getResourceAsStream("/image/product_image/" + imageName + ".jpg"));
        }
        view = new ImageView(image);
        view.setFitWidth(imageWidth);
        view.setFitHeight(imageHeight);

        return view;
    }

    public static void shoppingCartsTableProperty(TableView<CustomerCart> cart, TableColumn<CustomerCart, String> name,
                                                  TableColumn<CustomerCart, Integer> quantity, TableColumn<CustomerCart, Double> price,
                                                  ObservableList<CustomerCart> item) {
        name.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        cart.setItems(item);
    }

    private static double format(double price){
        return Math.round(price * 100.0) / 100.0;
    }

    public static String getFooterText() {
        return "Copyright \u00a9 2018. All right reserved. Powered by M.Keita Platform";
    }
}
