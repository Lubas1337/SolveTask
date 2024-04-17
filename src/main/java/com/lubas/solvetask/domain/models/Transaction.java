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
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_transaction")
    @SequenceGenerator(name = "s_transaction", allocationSize = 1)
    private Long id;
    private Double sum;
    private String currencyShortname;
    private LocalDateTime datetime;
    private String expenseCategory;
    @ManyToOne(fetch = FetchType.EAGER)
    private Account accountFrom;
    @ManyToOne(fetch = FetchType.EAGER)
    private Account accountTo;
    private Boolean limitExceeded;
    private Double limitRemaining;
}
