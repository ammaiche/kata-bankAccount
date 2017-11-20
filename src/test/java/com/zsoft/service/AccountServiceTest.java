package com.zsoft.service;

import com.zsoft.domain.Account;
import com.zsoft.domain.Client;
import com.zsoft.domain.Transaction;
import com.zsoft.domain.TransactionType;
import com.zsoft.exceptions.AccountNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class AccountServiceTest {

    static Client client;
    AccountService accountService = new AccountService();

    @BeforeClass
    public static void init(){

        client = Client.getInstance();
    }
    @Test
    public void createAccount() throws Exception {

        Account account = new Account();
        account.setNumber("1234");
        account.setBalance(1500);

        Transaction transaction = new Transaction();
        transaction.setTransactionId("01");
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setDate(LocalDateTime.now());
        transaction.setAmount(200);
        transaction.setBalance(1500);

        account.getTransactions().add(transaction);
        accountService.createAccount(account);

        Account accountGet = client.getAccounts().get("1234");

        assertEquals(accountGet.getNumber(), "1234");
        assertEquals(accountGet.getBalance(), 1500,0);
        assertEquals(transaction, accountGet.getTransactions().stream().findFirst().get());

        client.getAccounts().remove("1234");
    }

    @Test
    public void deleteAccount() throws Exception {

        Account account = new Account();
        account.setNumber("1234");
        account.setBalance(1500);

        accountService.createAccount(account);
        accountService.deleteAccount("1234");

        assertEquals(client.getAccounts().containsKey("1234"), false);
    }

    @Test(expected = AccountNotFoundException.class)
    public void deleteAccountWithException() throws Exception {

        accountService.deleteAccount("0000");
    }
    @Test
    public void updateAccount() throws Exception {

        Account account = new Account();
        account.setNumber("1234");
        account.setBalance(1500);

        accountService.createAccount(account);

        Account account1 = new Account();
        account1.setNumber("1234");
        account1.setBalance(3000);

        accountService.updateAccount(account1);

        account = client.getAccounts().get("1234");

        assertEquals(account.getBalance(), 3000, 0);

        client.getAccounts().remove("1234");
    }

    @Test(expected = AccountNotFoundException.class)
    public void updateAccountWithException() throws Exception {

        Account account = new Account();
        account.setNumber("1234");
        account.setBalance(1500);

        accountService.updateAccount(account);
    }

    @Test
    public void getAccount() throws Exception {

        Account account = new Account();
        account.setNumber("007");
        account.setBalance(1500);

        Transaction transaction = new Transaction();
        transaction.setTransactionId("01");
        transaction.setType(TransactionType.DEPOSIT);
        transaction.setDate(LocalDateTime.now());
        transaction.setAmount(200);
        transaction.setBalance(1500);

        account.getTransactions().add(transaction);
        accountService.createAccount(account);

        Account account1 = accountService.getAccount("007");
        assertEquals(account1.getNumber(), "007");
        assertEquals(account1.getBalance(),  1500, 0);
        assertEquals(account1.getTransactions().stream().findFirst().get(), transaction);

        client.getAccounts().remove("007");
    }

    @Test(expected = AccountNotFoundException.class)
    public void getAccountWithException() throws Exception {
        accountService.getAccount("007");
    }
}