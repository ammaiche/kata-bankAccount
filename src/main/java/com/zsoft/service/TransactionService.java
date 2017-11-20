package com.zsoft.service;

import com.zsoft.exceptions.AccountNotFoundException;
import com.zsoft.exceptions.AmountNotValidException;
import com.zsoft.exceptions.BalanceNotSufficientException;

public interface TransactionService {

    public double deposit(String accountNumber, double amount) throws AmountNotValidException,AccountNotFoundException;
    public double withdraw(String accountNumber, double amount) throws AccountNotFoundException, BalanceNotSufficientException,
            AmountNotValidException;

}
