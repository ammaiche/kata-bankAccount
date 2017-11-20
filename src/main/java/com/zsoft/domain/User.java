package com.zsoft.domain;

import java.util.HashMap;

public class User {

    private String firstName;
    private String lastName;
    private String address;
    HashMap<String, Account> accounts;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }
}
