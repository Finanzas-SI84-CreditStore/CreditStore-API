package com.creditstore.CreditStore.accounts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal purchaseValue;
    private String interestType;
    private String capitalizationPeriod;
    private Integer interestPeriod;
    private BigDecimal interestRate;
    private String creditType;
    private Integer installmentCount;
    private Boolean gracePeriod;
    private Integer gracePeriodLength;
}
