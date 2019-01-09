package com.keitam.kayak.model;

import javax.persistence.*;

@Entity
@Table(name = "Kayak")
@Access(AccessType.FIELD)
public class KayakUser {
    @Id
    @GeneratedValue
    @Column(name = "UserID", unique = true, nullable = false)
    private Long userID;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "Username")
    private String userName;
    @Column(name = "Password")
    private String password;
    @Column(name = "UserType")
    private String userType;

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
