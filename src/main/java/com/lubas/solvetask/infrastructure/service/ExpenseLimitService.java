package com.lubas.solvetask.infrastructure.service;

import com.lubas.solvetask.domain.DTOs.ExpenseLimitDto;
import com.lubas.solvetask.domain.DTOs.TransactionDto;
import com.lubas.solvetask.domain.models.Account;
import com.lubas.solvetask.domain.models.ExpenseLimit;
import com.lubas.solvetask.domain.repository.AccountRepository;
import com.lubas.solvetask.domain.repository.ExpenseLimitRepository;
import com.lubas.solvetask.infrastructure.service.dtos.LimitWithTransactionDTO;
import com.lubas.solvetask.infrastructure.service.mappers.ExpenseLimitMapper;
import com.lubas.solvetask.infrastructure.service.mappers.LimitWithTransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ExpenseLimitService {
    private final ExpenseLimitRepository limitRepository;
    private final AccountRepository accountRepository;

    public ExpenseLimit saveLimit(ExpenseLimitDto dto) {
        ExpenseLimit limit = ExpenseLimitMapper.toEntity(dto);
        limit.setDatetime(LocalDateTime.now());
        limit.setAccount(accountRepository.findByAccountNumber(dto.getAccountNumber()));
        return limitRepository.save(limit);
    }

    public List<LimitWithTransactionDTO> findAllLimitsWithTransactions(Integer accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        Long accountId = account.getId();
        List<Object[]> limAndTransList = limitRepository.findLimAndTransList(accountId);

        List<LimitWithTransactionDTO> result = new ArrayList<>();

        for (Object[] limAndTrans : limAndTransList) {
            ExpenseLimitDto expenseLimitDto = LimitWithTransactionMapper.toDtoLimit(limAndTrans);
            TransactionDto transactionDto = LimitWithTransactionMapper.toDtoTransaction(limAndTrans, accountRepository);

            LimitWithTransactionDTO limAndTransDto = LimitWithTransactionDTO.builder()
                    .limitDto(expenseLimitDto)
                    .transactionDto(transactionDto)
                    .build();

            result.add(limAndTransDto);
        }
        return result;
    }

    public ExpenseLimit createLimitIfNotExistsThisMonth(String category, Account account) {
        YearMonth currentMonth = YearMonth.now();
        LocalDateTime startOfMonth = currentMonth.atDay(1).atStartOfDay();
        return limitRepository.findLastLimThisMonth(category, account.getId())
                .orElseGet(() ->
                        limitRepository.save(
                                ExpenseLimit.builder()
                                        .sum(0.)
                                        .currencyShortname("USD")
                                        .datetime(startOfMonth)
                                        .expenseCategory(category)
                                        .account(account)
                                        .build()
                        )
                );
    }
}
