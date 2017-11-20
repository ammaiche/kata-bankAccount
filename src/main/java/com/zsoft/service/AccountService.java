package com.zsoft.service;

import com.zsoft.domain.Account;
import com.zsoft.domain.Client;

public class AccountService {

    Client currentClient;

    public  AccountService(){

        currentClient = Client.getInstance();
    }
    public void createAccount(Account account){

        currentClient.getAccounts().put(account.getNumber(), account);
    }
    public void deleteAccount(String accountNumber) throws Exception {

        if(currentClient.getAccounts().keySet().contains(accountNumber)){

            currentClient.getAccounts().remove(accountNumber);

        }else throw new Exception();
    }

    public Account getAccount(String accountNumber) throws Exception {

        if(currentClient.getAccounts().keySet().contains(accountNumber)){

            return currentClient.getAccounts().get(accountNumber);

        }else throw new Exception();
    }
    public void updateAccount(Account account) throws Exception {

        if(currentClient.getAccounts().keySet().contains(account.getNumber())) {

             currentClient.getAccounts().put(account.getNumber(), account);

        }else throw new Exception();

    }
    public double deposit(String accountNumber, double amount) throws Exception {

        if(currentClient.getAccounts().keySet().contains(accountNumber)) {

            Account account =currentClient.getAccounts().get(accountNumber);
            account.setBalance(account.getBalance()+amount);
            return account.getBalance();

        }else throw new Exception();
    }

    public double withdraw(String accountNumber, double amount) throws Exception {

        if(currentClient.getAccounts().keySet().contains(accountNumber)) {

            Account account =currentClient.getAccounts().get(accountNumber);
            account.setBalance(account.getBalance()-amount);
            return account.getBalance();
        }else throw new Exception();
    }
}
