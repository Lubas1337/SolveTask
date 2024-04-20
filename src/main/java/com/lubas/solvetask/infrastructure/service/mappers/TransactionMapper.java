package com.lubas.solvetask.infrastructure.service.mappers;

import com.lubas.solvetask.domain.DTOs.TransactionDto;
import com.lubas.solvetask.domain.models.Transaction;

public class TransactionMapper {
    public static TransactionDto mapToTransactionDto(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .sum(transaction.getSum())
                .currencyShortname(transaction.getCurrencyShortname())
                .datetime(String.valueOf(transaction.getDatetime()))
                .expenseCategory(transaction.getExpenseCategory())
                .accountFromNumber(transaction.getAccountFrom().getAccountNumber())
                .accountToNumber(transaction.getAccountTo().getAccountNumber())
                .limitRemaining(transaction.getLimitRemaining())
                .build();
    }
}
