package com.lubas.solvetask.domain.DTOs;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpenseLimitDto implements Serializable {
    private Long id;
    private Double sum;
    private String currencyShortname;
    private String datetime;
    private String expenseCategory;
    private Integer accountNumber;
}