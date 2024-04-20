package com.lubas.solvetask.infrastructure.controller;

import com.lubas.solvetask.domain.DTOs.ExpenseLimitDto;
import com.lubas.solvetask.domain.DTOs.TransactionDto;
import com.lubas.solvetask.domain.models.Account;
import com.lubas.solvetask.domain.models.ExpenseLimit;
import com.lubas.solvetask.domain.models.Transaction;
import com.lubas.solvetask.domain.repository.AccountRepository;
import com.lubas.solvetask.domain.repository.TransactionRepository;
import com.lubas.solvetask.infrastructure.service.ExpenseLimitService;
import com.lubas.solvetask.infrastructure.service.TransactionService;
import com.lubas.solvetask.infrastructure.service.dtos.LimitWithTransactionDTO;
import com.lubas.solvetask.infrastructure.service.mappers.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class RestController {
    private final ExpenseLimitService service;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;

    @PostMapping("/newLimit")
    public ExpenseLimit post(@RequestBody ExpenseLimitDto dto) {
        return service.saveLimit(dto);
    }

    @GetMapping("/getLimitOnAccount/{accountNumber}")
    public List<LimitWithTransactionDTO> get(@PathVariable Integer accountNumber) {
        return service.findAllLimitsWithTransactions(accountNumber);
    }

    @PostMapping("/newAccount/{accountNumber}")
    public Account createAccount(@PathVariable Integer accountNumber) {
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        return accountRepository.save(account);
    }

    @GetMapping("/getByIdAccount/{id}")
    public Account find(@PathVariable Long id) {
        return accountRepository.findById(id
        ).orElseThrow(null);
    }

    @GetMapping("/findTransaction/{id}")
    public Transaction findT(@PathVariable Long id) {
        return transactionRepository.findById(id).orElseThrow(null);
    }

    @PostMapping("/createTransaction")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto requestDto) {
        Transaction transaction = transactionService.createTransaction(
                requestDto.getSum(),
                requestDto.getCurrencyShortname(),
                requestDto.getExpenseCategory(),
                requestDto.getAccountFromNumber(),
                requestDto.getAccountToNumber()
        );

        TransactionDto responseDto = TransactionMapper.mapToTransactionDto(transaction);
        return ResponseEntity.ok(responseDto);
    }
}
