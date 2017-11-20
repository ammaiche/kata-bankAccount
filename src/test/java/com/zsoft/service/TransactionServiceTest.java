package com.zsoft.service;

import com.zsoft.domain.Account;
import com.zsoft.domain.Transaction;
import com.zsoft.domain.TransactionType;
import com.zsoft.exceptions.AccountNotFoundException;
import com.zsoft.exceptions.AmountNotValidException;
import com.zsoft.exceptions.BalanceNotSufficientException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class TransactionServiceTest {

    private TransactionService transactionService = new TransactionServiceImpl();
    private AccountService accountService = new AccountServiceImpl();

    @Test
    public void deposit() throws Exception {

        Account account = new Account();
        account.setNumber("007");
        account.setBalance(1500);

        accountService.createAccount(account);

        transactionService.deposit(account.getNumber(), 500);
        account = accountService.getAccount(account.getNumber());

        Assert.assertEquals(account.getBalance(), 2000,0);

        Transaction transaction = account.getTransactions().stream().findFirst().get();

        Assert.assertEquals(transaction.getAmount(),500,0);
        Assert.assertEquals(transaction.getBalance(),2000,0);
        Assert.assertEquals(transaction.getType(), TransactionType.DEPOSIT);
        Assert.assertEquals(transaction.getDate().toLocalDate(), LocalDateTime.now().toLocalDate());

        accountService.deleteAccount(account.getNumber());
    }
    @Test(expected = AccountNotFoundException.class)
    public void depositAccountNotFoundException() throws Exception {

        transactionService.deposit("007", 500);
    }

    @Test(expected = AmountNotValidException.class)
    public void depositAmountNotValidException() throws Exception {

        Account account = new Account();
        account.setNumber("007");
        account.setBalance(1500);

        accountService.createAccount(account);
        transactionService.deposit(account.getNumber(), -50);

        accountService.deleteAccount(account.getNumber());
    }
    @Test()
    public void withdraw() throws Exception {

        Account account = new Account();
        account.setNumber("007");
        account.setBalance(1500);

        accountService.createAccount(account);

        transactionService.withdraw(account.getNumber(), 500);
        account = accountService.getAccount(account.getNumber());

        Assert.assertEquals(account.getBalance(), 1000,0);

        Transaction transaction = account.getTransactions().stream().findFirst().get();

        Assert.assertEquals(transaction.getAmount(),500,0);
        Assert.assertEquals(transaction.getBalance(),1000,0);
        Assert.assertEquals(transaction.getType(), TransactionType.WITHDRAW);
        Assert.assertEquals(transaction.getDate().toLocalDate(), LocalDateTime.now().toLocalDate());

        accountService.deleteAccount(account.getNumber());
    }
    @Test(expected = AccountNotFoundException.class)
    public void withdrawAccountNotFoundException() throws Exception {

        transactionService.withdraw("007", 500);

    }
    @Test(expected = AmountNotValidException.class)
    public void withdrawAmountNotValidException() throws Exception {

        Account account = new Account();
        account.setNumber("007");
        account.setBalance(1500);

        accountService.createAccount(account);

        transactionService.withdraw(account.getNumber(), 0);

        accountService.deleteAccount(account.getNumber());
    }

    @Test(expected = BalanceNotSufficientException.class)
    public void withdrawBalanceNotSufficientException() throws Exception {

        Account account = new Account();
        account.setNumber("007");
        account.setBalance(1500);

        accountService.createAccount(account);

        transactionService.withdraw(account.getNumber(), 1500.1);

        accountService.deleteAccount(account.getNumber());

    }
}