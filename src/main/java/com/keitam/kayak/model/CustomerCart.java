package com.keitam.kayak.model;

import javafx.beans.property.*;
import javafx.scene.image.ImageView;

public class CustomerCart {
    private LongProperty itemID;
    private StringProperty itemName;
    private IntegerProperty quantity;
    private DoubleProperty price;
    private ImageView image;

    public CustomerCart(String name, int quantity, double price){
        this.itemName = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
    }
    public CustomerCart (Long productID, String name, int quantity, double price, ImageView view) {
        this.itemID = new SimpleLongProperty(productID);
        this.itemName = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.price = new SimpleDoubleProperty(price);
        this.image = view;
    }

    public long getItemID() {
        return itemID.get();
    }

    public LongProperty itemIDProperty() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID.set(itemID);
    }

    public String getItemName() {
        return itemName.get();
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public int getQuantity() {
        return quantity.get();
    }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
