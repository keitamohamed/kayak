package com.keitam.kayak.model;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "User")
@Access(AccessType.FIELD)
@RequiredArgsConstructor
public class User {
    @Id
    @Column(name = "UserID", unique = true, nullable = false)
    private Long userID;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "UserName", nullable = false, unique = true)
    private String userName;
    @Column(name = "Password", unique = true, nullable = false)
    private String password;
    @Column(name = "UserType")
    private String userType;

    public User(Long userID, String firstName, String lastName, String userName, String password, String userType) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User Info: " + userID + ", " + firstName + " " + lastName +
                ", and type " + userType;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
