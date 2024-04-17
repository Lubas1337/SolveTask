package com.lubas.solvetask.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exchange_rate")
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_exchange")
    @SequenceGenerator(name = "s_exchange", allocationSize = 1)
    private Long id;
    private String baseCurrency;
    private String targetCurrency;
    private double rate;
    private LocalDateTime date;
    private String type;

}
