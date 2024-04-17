package com.lubas.solvetask.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_account")
    @SequenceGenerator(name = "s_account", allocationSize = 1)
    private Long id;

    private Integer accountNumber;
}
