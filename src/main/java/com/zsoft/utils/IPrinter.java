package com.zsoft.utils;

import com.zsoft.domain.Account;
import com.zsoft.domain.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface IPrinter {

    default void print(Account account){

        System.out.println("Account number" + account.getNumber());
        System.out.println("Account Balance" + account.getBalance());

        account.getTransactions().forEach(transaction -> {

            System.out.println("Amount "+ transaction.getAmount());
            System.out.println("Date " +transaction.getDate());
            System.out.println("Type "+transaction.getType());
            System.out.println("Balance "+transaction.getBalance());
        });
    }

    default void print(Account account, LocalDateTime  startDate, LocalDateTime endDate){

        List<Transaction> transactions = account.getTransactions().stream().filter(transaction ->
            transaction.getDate().compareTo(startDate)>=0 && transaction.getDate().compareTo(endDate)<=0
        ).collect(Collectors.toList());

        System.out.println("Account number" + account.getNumber());
        System.out.println("Account Balance" + account.getBalance());

        transactions.forEach(transaction -> {

            System.out.println("Amount "+ transaction.getAmount());
            System.out.println("Date " +transaction.getDate());
            System.out.println("Type "+transaction.getType());
            System.out.println("Balance "+transaction.getBalance());
        });
    }

}
