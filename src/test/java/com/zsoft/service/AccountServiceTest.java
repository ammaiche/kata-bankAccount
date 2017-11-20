package com.zsoft.service;

import com.zsoft.domain.Account;
import com.zsoft.domain.Client;
import com.zsoft.domain.Transaction;
import com.zsoft.exceptions.AccountNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;

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

        account.getTransactions().add(new Transaction());
        account.getTransactions().add(new Transaction());

        accountService.createAccount(account);

        Account accountGet = client.getAccounts().get("1234");

        assertEquals(accountGet.getNumber(), "1234");
        assertEquals(accountGet.getBalance(), 1500,0);
        assertEquals(2, accountGet.getTransactions().size());

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
        account.setBalance(3000);

        accountService.updateAccount(account);

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
        fail("Not implemented");
    }
    @Test
    public void deposit() throws Exception {
        fail("Not implemented");
    }
    @Test
    public void withdraw() throws Exception {
        fail("Not implemented");
    }
}