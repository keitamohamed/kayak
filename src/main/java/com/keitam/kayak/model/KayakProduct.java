package com.keitam.kayak.model;

import javax.persistence.*;

@Entity
@Table(name = "Product")
@Access(AccessType.FIELD)
public class KayakProduct {

    @Id
    @GeneratedValue
    @Column(name = "ProductID", unique = true, nullable = false)
    private Long productID;
    @Column(name = "PName")
    private String pName;
    @Column(name = "Quantity")
    private String quantity;
    @Column(name = "Price")
    private double price;
    @Column(name = "ImageName")
    private String imageName;

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
