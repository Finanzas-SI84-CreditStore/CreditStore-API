package com.creditstore.CreditStore.accounts.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
public class PayResponse {

    private Integer id;

    private double amount;

    private LocalDate date;
}
