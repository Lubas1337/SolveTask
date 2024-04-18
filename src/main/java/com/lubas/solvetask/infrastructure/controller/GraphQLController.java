package com.lubas.solvetask.infrastructure.controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.lubas.solvetask.domain.models.Account;
import com.lubas.solvetask.domain.models.Transaction;
import com.lubas.solvetask.domain.repository.AccountRepository;
import com.lubas.solvetask.domain.repository.TransactionRepository;
import com.lubas.solvetask.infrastructure.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Component
@RequiredArgsConstructor
public class GraphQLController implements GraphQLQueryResolver {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @SchemaMapping
    public Account find(Long id) {
        return accountRepository.findById(id).orElseThrow(null);
    }

    @SchemaMapping
    public Transaction create(Double sum, String currencyShortname, String datetime,
                                         String expenseCategory, Integer accountFromNumber, Integer accountToNumber) {
        Transaction transaction = transactionService.createTransaction(sum, currencyShortname, datetime,
                expenseCategory, accountFromNumber, accountToNumber);

        return transactionRepository.save(transaction);
    }

    @SchemaMapping
    public Transaction findT(Long id) {
        return transactionRepository.findById(id).orElseThrow(null);
    }

    @SchemaMapping
    public String hello(String name){
        return "Hello" + name;
    }

    @QueryMapping
    public Account createAccount(Integer accountNumber) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        return accountRepository.save(account);
    }
}
