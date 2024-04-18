package com.lubas.solvetask.infrastructure.controller;

import com.lubas.solvetask.domain.DTOs.ExpenseLimitDto;
import com.lubas.solvetask.domain.models.ExpenseLimit;
import com.lubas.solvetask.infrastructure.service.ExpenseLimitService;
import com.lubas.solvetask.infrastructure.service.dtos.LimitWithTransactionDTO;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/post")
    public ExpenseLimit post(@RequestBody ExpenseLimitDto dto) {
        return service.saveLimit(dto);
    }

    @GetMapping("/get/{accountNumber}")
    public List<LimitWithTransactionDTO> get(@PathVariable Integer accountNumber) {
        return service.findAllLimitsWithTransactions(accountNumber);
    }
}
