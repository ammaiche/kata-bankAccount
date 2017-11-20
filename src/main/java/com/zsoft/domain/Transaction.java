package com.zsoft.domain;


import java.time.LocalDateTime;

public class Transaction {


    private String transactionId;
    private TransactionType type;
    private LocalDateTime date;
    private double amount;
    private double balance;


    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        if (Double.compare(that.amount, amount) != 0) return false;
        if (Double.compare(that.balance, balance) != 0) return false;
        if (!transactionId.equals(that.transactionId)) return false;
        if (type != that.type) return false;
        return date.equals(that.date);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = transactionId.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + date.hashCode();
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
