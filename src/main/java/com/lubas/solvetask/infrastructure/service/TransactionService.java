package com.lubas.solvetask.infrastructure.service;

import com.lubas.solvetask.domain.models.Account;
import com.lubas.solvetask.domain.models.ExpenseLimit;
import com.lubas.solvetask.domain.models.Transaction;
import com.lubas.solvetask.domain.repository.AccountRepository;
import com.lubas.solvetask.domain.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/rest/v0.1")
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final ExpenseLimitService limitService;
    private final ExchangeRateService exchangeRateService;

    public Transaction createTransaction(Double sum, String currencyShortname,
                                         String expenseCategory, Integer accountFromNumber, Integer accountToNumber) {

        Account accountFrom = accountRepository.findByAccountNumber(accountFromNumber);
        Account accountTo = accountRepository.findByAccountNumber(accountToNumber);

        Transaction transaction = Transaction.builder()
                .sum(sum)
                .currencyShortname(currencyShortname)
                .datetime(LocalDateTime.now())
                .expenseCategory(expenseCategory)
                .accountFrom(accountFrom)
                .accountTo(accountTo)
                .build();

        setLimitRemaining(transaction);
        setLimitExceeded(transaction);

        return transactionRepository.save(transaction);
    }

    private void setLimitExceeded(Transaction transaction) {
        boolean limitExceeded = transaction.getLimitRemaining() < 0;
        transaction.setLimitExceeded(limitExceeded);
    }

    private void setLimitRemaining(Transaction transaction) {
        Transaction lastTransaction = transactionRepository.findLastThisMonth(
                transaction.getExpenseCategory(),
                transaction.getAccountFrom().getId()
        ).orElseGet(() -> Transaction.builder()
                .limitRemaining(0.)
                .datetime(LocalDateTime.of(
                        YearMonth.now().atDay(1),
                        LocalTime.MIN
                ))
                .build());

        ExpenseLimit limit = limitService.createLimitIfNotExistsThisMonth(
                transaction.getExpenseCategory(), transaction.getAccountFrom());

        double exchangeRateIfKzt =
                transaction.getCurrencyShortname().equals("KZT") ? exchangeRateService.checkToday() : 1;

        double limitSumIfLimitLaterThanLastTrans =
                !limit.getDatetime().isBefore(lastTransaction.getDatetime()) ? limit.getSum() : 0.;

        double limitRemaining = limitSumIfLimitLaterThanLastTrans + lastTransaction.getLimitRemaining()
                - transaction.getSum() * exchangeRateIfKzt;

        transaction.setLimitRemaining(limitRemaining);
    }
}
