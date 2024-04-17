package com.lubas.solvetask.infrastructure.service.mappers;

import com.lubas.solvetask.domain.DTOs.ExpenseLimitDto;
import com.lubas.solvetask.domain.DTOs.TransactionDto;
import com.lubas.solvetask.domain.repository.AccountRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LimitWithTransactionMapper {
    public static ExpenseLimitDto toDtoLimit (Object[] limAndTrans) {
        long id = ((BigInteger) limAndTrans[0]).longValue();
        double sum = ((BigDecimal) limAndTrans[1]).doubleValue();
        String currencyShortname = (String) limAndTrans[2];
        Timestamp datetime = (Timestamp) limAndTrans[3];
        String expenseCategory = (String) limAndTrans[4];

        return ExpenseLimitDto.builder()
                .id(id)
                .sum(sum)
                .currencyShortname(currencyShortname)
                .datetime(datetime.toLocalDateTime().toString())
                .expenseCategory(expenseCategory)
                .build();
    }

    public static TransactionDto toDtoTransaction (Object[] limAndTrans, AccountRepository accountRepository) {
        long id = ((BigInteger) limAndTrans[5]).longValue();
        double sum = ((BigDecimal) limAndTrans[6]).doubleValue();
        String currencyShortname = (String) limAndTrans[7];
        Timestamp datetime = (Timestamp) limAndTrans[8];
        String expenseCategory = (String) limAndTrans[9];
        long accountFromId = ((BigInteger) limAndTrans[10]).longValue();
        long accountToId = ((BigInteger) limAndTrans[11]).longValue();
        boolean limitExceeded = (boolean) limAndTrans[12];
        double limitRemaining = ((BigDecimal) limAndTrans[13]).doubleValue();

        int accountFromNumber = accountRepository.findById(accountFromId).get().getAccountNumber();
        int accountToNumber = accountRepository.findById(accountToId).get().getAccountNumber();

        return TransactionDto.builder()
                .id(id)
                .sum(sum)
                .currencyShortname(currencyShortname)
                .datetime(datetime.toLocalDateTime().toString())
                .expenseCategory(expenseCategory)
                .accountFromNumber(accountFromNumber)
                .accountToNumber(accountToNumber)
                .limitExceeded(limitExceeded)
                .limitRemaining(limitRemaining)
                .build();
    }
}
