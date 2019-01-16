package com.keitam.kayak.model;

import javax.persistence.*;

@Entity
@Table(name = "Address")
@Access(AccessType.FIELD)
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "userID")
    private Long userID;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "Zip")
    private int zipCode;

    public UserAddress(Long userID, String address, String city, String state, int zipCode) {
        this.userID = userID;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }


    public Long getId() {
        return id;
    }

    public Long getUserID() {
        return userID;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getZipCode() {
        return zipCode;
    }
}
