package com.lubas.solvetask.domain.DTOs;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private Long id;
    private Double sum;
    private String currencyShortname;
    private String datetime;
    private String expenseCategory;
    private Integer accountFromNumber;
    private Integer accountToNumber;
    private Boolean limitExceeded;
    private Double limitRemaining;

}
