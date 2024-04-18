package com.lubas.solvetask.infrastructure.service.mappers;

import com.lubas.solvetask.domain.DTOs.ExpenseLimitDto;
import com.lubas.solvetask.domain.models.ExpenseLimit;

public class ExpenseLimitMapper {
    public static ExpenseLimit toEntity(ExpenseLimitDto dto) {
        return ExpenseLimit.builder()
                .sum(dto.getSum())
                .currencyShortname(dto.getCurrencyShortname())
                .expenseCategory(dto.getExpenseCategory())
                .build();
    }
}
