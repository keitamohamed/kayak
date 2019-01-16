package com.keitam.kayak.model;

import javax.persistence.*;

@Entity
@Table(name = "Address")
public class UserAddress {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "userID")
    private Long userID;
    @Column(name = "userAddress")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "zipCode")
    private String zipCode;


}
