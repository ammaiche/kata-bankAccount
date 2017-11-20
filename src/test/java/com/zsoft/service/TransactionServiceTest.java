package com.zsoft.service;

import com.zsoft.domain.Account;
import com.zsoft.domain.Client;
import com.zsoft.domain.Transaction;
import com.zsoft.domain.TransactionType;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;

public class TransactionServiceTest {

    private TransactionService transactionService = new TransactionService();
    private AccountService accountService = new AccountService();


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
    @Test
    public void depositAccountNotFoundException() throws Exception {

        Account account = new Account();
        account.setNumber("007");
    }

    @Test
    public void depositAmountNotValidException() throws Exception {

    }
    @Test
    public void withdraw() throws Exception {

    }
    @Test
    public void withdrawAccountNotFoundException() throws Exception {
    }
    @Test
    public void withdrawAmountNotValidException() throws Exception {
    }

    @Test
    public void withdrawBalanceNotSufficientException() throws Exception {
    }
}