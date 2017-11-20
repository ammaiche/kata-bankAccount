package com.zsoft.domain;

import java.util.Set;

public class Account {

    private String number;
    private double balance;
    private Set<Transaction> transactions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (Double.compare(account.balance, balance) != 0) return false;
        return number.equals(account.number);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = number.hashCode();
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
