package com.lubas.solvetask.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class ExpenseLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_expense_limit")
    @SequenceGenerator(name = "s_expense_limit", allocationSize = 1)
    private Long id;
    private Double sum;
    private String currencyShortname;
    private LocalDateTime datetime;
    private String expenseCategory;
    @ManyToOne(fetch = FetchType.EAGER)
    private Account account;
}
