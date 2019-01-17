package com.keitam.kayak.model;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Address")
@Access(AccessType.FIELD)
@RequiredArgsConstructor
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "user_ID")
    private Long userID;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "Zip")
    private int zipCode;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "User_ID", insertable = false, updatable = false)
    private User user;

    public UserAddress(Long userID, String address, String city,
                       String state, int zipCode, User user) {
        this.userID = userID;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
