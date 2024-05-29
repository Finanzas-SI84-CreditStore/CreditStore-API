package com.creditstore.CreditStore.account.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequest {
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
