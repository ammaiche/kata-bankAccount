package com.zsoft.domain;

import java.util.HashMap;

public class Client  {

    private static final Client instance = new Client();

    private String clientId;
    private String firstName;
    private String lastName;
    private String address;
    HashMap<String, Account> accounts;

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

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        if (!clientId.equals(client.clientId)) return false;
        if (!firstName.equals(client.firstName)) return false;
        if (!lastName.equals(client.lastName)) return false;
        return address.equals(client.address);
    }

    @Override
    public int hashCode() {
        int result = clientId.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
