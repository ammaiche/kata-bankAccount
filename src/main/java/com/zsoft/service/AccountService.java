package com.zsoft.service;

import com.zsoft.domain.Account;
import com.zsoft.exceptions.AccountNotFoundException;

public interface AccountService {

     void createAccount(Account account);
     void deleteAccount(String accountNumber) throws AccountNotFoundException;
     void updateAccount(Account account) throws AccountNotFoundException;
     Account getAccount(String accountNumber) throws AccountNotFoundException;
}
