package com.zsoft.service;

import com.zsoft.domain.Account;
import com.zsoft.domain.Client;
import com.zsoft.domain.Transaction;
import com.zsoft.domain.TransactionType;
import com.zsoft.exceptions.AccountNotFoundException;
import com.zsoft.exceptions.AmountNotValidException;
import com.zsoft.exceptions.BalanceNotSufficientException;

import java.time.LocalDateTime;
import java.util.Random;

public class TransactionServiceImpl implements TransactionService{

    private Client currentClient;

    public  TransactionServiceImpl(){

        currentClient = Client.getInstance();
    }

    public double deposit(String accountNumber, double amount) throws AmountNotValidException,AccountNotFoundException {

        if(currentClient.getAccounts().keySet().contains(accountNumber)) {

            Account account =currentClient.getAccounts().get(accountNumber);

            if(amount<=0) throw  new AmountNotValidException("Amount is not valid");
            else{

                account.setBalance(account.getBalance()+amount);

                Transaction transaction = new Transaction();
                transaction.setTransactionId(""+new Random().nextDouble());
                transaction.setAmount(amount);
                transaction.setBalance(account.getBalance());
                transaction.setDate(LocalDateTime.now());
                transaction.setType(TransactionType.DEPOSIT);
                account.getTransactions().add(transaction);

                return account.getBalance();
            }
        }else throw new AccountNotFoundException("Account doesn't exist");
    }

    public double withdraw(String accountNumber, double amount) throws AccountNotFoundException, BalanceNotSufficientException,
            AmountNotValidException {

        if(currentClient.getAccounts().keySet().contains(accountNumber)) {

            Account account =currentClient.getAccounts().get(accountNumber);

            if(amount<=0) throw new AmountNotValidException("Amount is not valid");
            else if(account.getBalance()-amount <0) throw  new BalanceNotSufficientException("Balance is not suffcient");
            else{

                account.setBalance(account.getBalance()-amount);

                Transaction transaction = new Transaction();
                transaction.setTransactionId(""+new Random().nextDouble());
                transaction.setAmount(amount);
                transaction.setBalance(account.getBalance());
                transaction.setDate(LocalDateTime.now());
                transaction.setType(TransactionType.WITHDRAW);
                account.getTransactions().add(transaction);

                return account.getBalance();
            }
        }else throw new AccountNotFoundException("Account doesn't exist");
    }
}
