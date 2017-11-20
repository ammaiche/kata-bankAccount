package com.zsoft.service;

import com.zsoft.domain.Account;
import com.zsoft.domain.Client;
import com.zsoft.exceptions.AccountNotFoundException;

public class AccountService {

    Client currentClient;

    public  AccountService(){

        currentClient = Client.getInstance();
    }
    public void createAccount(Account account){

        currentClient.getAccounts().put(account.getNumber(), account);
    }
    public void deleteAccount(String accountNumber) throws AccountNotFoundException {

        if(currentClient.getAccounts().keySet().contains(accountNumber)){

            currentClient.getAccounts().remove(accountNumber);

        }else throw new AccountNotFoundException("Account not found");
    }
    public void updateAccount(Account account) throws AccountNotFoundException {

        if(currentClient.getAccounts().keySet().contains(account.getNumber())) {

             currentClient.getAccounts().put(account.getNumber(), account);

        }else throw new AccountNotFoundException("Account doesn't exist");
    }

    public Account getAccount(String accountNumber) throws AccountNotFoundException {

        if(currentClient.getAccounts().keySet().contains(accountNumber)){

            return currentClient.getAccounts().get(accountNumber);

        }else throw new AccountNotFoundException("Account doesn't exist");
    }
}
