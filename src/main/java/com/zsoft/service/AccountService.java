package com.zsoft.service;

import com.zsoft.domain.Account;
import com.zsoft.domain.Client;
import com.zsoft.exceptions.AccountNotFoundException;
import com.zsoft.exceptions.AmountNotValidException;
import com.zsoft.exceptions.BalanceNotSufficientException;

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

    public Account getAccount(String accountNumber) throws AccountNotFoundException {

        if(currentClient.getAccounts().keySet().contains(accountNumber)){

            return currentClient.getAccounts().get(accountNumber);

        }else throw new AccountNotFoundException("Account doesn't exist");
    }
    public void updateAccount(Account account) throws AccountNotFoundException {

        if(currentClient.getAccounts().keySet().contains(account.getNumber())) {

             currentClient.getAccounts().put(account.getNumber(), account);

        }else throw new AccountNotFoundException("Account doesn't exist");

    }
    public double deposit(String accountNumber, double amount) throws AmountNotValidException,AccountNotFoundException {

        if(currentClient.getAccounts().keySet().contains(accountNumber)) {

            Account account =currentClient.getAccounts().get(accountNumber);

            if(amount<=0) throw  new AmountNotValidException("Amount is not valid");
            else{

                account.setBalance(account.getBalance()+amount);
                return account.getBalance();
            }
        }else throw new AccountNotFoundException("Account doesn't exist");
    }

    public double withdraw(String accountNumber, double amount) throws AccountNotFoundException,BalanceNotSufficientException {

        if(currentClient.getAccounts().keySet().contains(accountNumber)) {

            Account account =currentClient.getAccounts().get(accountNumber);
            if(account.getBalance()-amount <0) throw  new BalanceNotSufficientException("Balance is not suffcient");
            else{

                account.setBalance(account.getBalance()-amount);
                return account.getBalance();
            }
        }else throw new AccountNotFoundException("Account doesn't exist");
    }
}
