package com.lubas.solvetask.infrastructure.service.dtos;

import com.lubas.solvetask.domain.DTOs.ExpenseLimitDto;
import com.lubas.solvetask.domain.DTOs.TransactionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LimitWithTransactionDTO {
    private ExpenseLimitDto limitDto;
    private TransactionDto transactionDto;
}
