package com.zsoft.domain;

import java.util.HashMap;

public class Client  {

    private static final Client instance = new Client();

    HashMap<String, Account> accounts;
    private String firstName;
    private String lastName;
    private String address;

    public static Client getInstance(){
        return instance;
    }

    private Client(){
        accounts = new HashMap();
    }

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
