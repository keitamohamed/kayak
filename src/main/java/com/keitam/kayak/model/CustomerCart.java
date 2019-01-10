package com.keitam.kayak.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

public class CustomerCart {
    private IntegerProperty itemID;
    private StringProperty itemName;
    private IntegerProperty quantity;
    private DoubleProperty price;
    private ImageView image;

    public int getItemID() {
        return itemID.get();
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
