package com.keitam.kayak.util;

import com.keitam.kayak.model.CustomerCart;
import com.keitam.kayak.model.Product;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.text.NumberFormat;
import java.util.Random;

public class KayakUtil {
    public static final NumberFormat NUMBER_FORMAT = NumberFormat.getCurrencyInstance();

    private KayakUtil(){}

    public static void getFinanceInfo(ObservableList<CustomerCart> carts, Label totalItems,
                                      Label discountPer, Label itemsPrice, Label discountAmount,
                                      Label itemsTotalPrice) {
        totalItems.setText("" +  getTotalItemsCount(carts));
        discountPer.setText("" + getDiscountSaving(carts) + "%");
        itemsPrice.setText("" + NUMBER_FORMAT.format(getItemsPrice(carts)));
        discountAmount.setText("Discount Amt: " + NUMBER_FORMAT.format(format(getItemsPrice(carts) * getDiscountSaving(carts))));
        itemsTotalPrice.setText("Total Price: " + NUMBER_FORMAT.format(getTotalPrice(carts)));
    }

    private static double getDiscountSaving(ObservableList<CustomerCart> carts) {
        if (format(getItemsPrice(carts)) > 500)
            return .10;
        else if (format(getItemsPrice(carts)) > 200)
            return .05;
        return 0;
    }

    private static double getItemsPrice(ObservableList<CustomerCart> carts) {
        double totalPrice = 0;
        for (CustomerCart item : carts)
            totalPrice += item.getPrice();
        return totalPrice;
    }

    private static double getTotalPrice(ObservableList<CustomerCart> carts) {
        double saving = format(getItemsPrice(carts) * getDiscountSaving(carts));
        return format(getItemsPrice(carts) - saving);
    }

    public static Product getProductOriginalPrice(ObservableList<Product> products, Long productID){
        for (Product product : products){
            if (product.getProductID().equals(productID)) {
                return product;
            }
        }
        return null;
    }

    public static void updateCartOriginalPrice(ObservableList<CustomerCart> carts, ObservableList<Product> products) {
        carts.forEach(item -> {
            products.forEach(product -> {
                if (item.getItemID() == product.getProductID())
                    item.setPrice(product.getPrice());
            });
        });
    }

    private static int getTotalItemsCount(ObservableList<CustomerCart> carts) {
        return carts.stream().map(CustomerCart::getQuantity).mapToInt(Integer::intValue).sum();
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
        cart.refresh();
    }

    public static double format(double price){
        return Math.round(price * 100.0) / 100.0;
    }

    public static String getFooterText() {
        return "Copyright \u00a9 2018. All right reserved. Powered by M.Keita Platform";
    }

    public static int getGeneratedUserID() {
        Random random = new Random();
        return (random.nextInt(900000) + 900000);
    }
}
