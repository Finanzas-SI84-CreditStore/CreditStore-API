package com.creditstore.CreditStore.accounts.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequest {
    @NotEmpty(message = "Valor de compra es requerido")
    private BigDecimal purchaseValue;

    @NotEmpty(message = "Tipo de tasa es requerido")
    private String interestType;

    @NotEmpty(message = "Capitaizacion de tasa es requerido")
    private String capitalizationPeriod;

    @NotEmpty(message = "Tiempo de tasa es requerido")
    private Integer interestPeriod;

    @NotEmpty(message = "Valor de tasa es requerido")
    private BigDecimal interestRate;

    @NotEmpty(message = "Tipo de credito es requerido")
    private String creditType;

    @NotEmpty(message = "Numero de cuotas es requerido")
    private Integer installmentCount;

    @NotEmpty(message = "Plazo de gracia es requerido")
    private Boolean gracePeriod;

    @NotEmpty(message = "Periodo de gracia es requerido")
    private Integer gracePeriodLength;
}
