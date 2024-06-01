package com.creditstore.CreditStore.accounts.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class AccountRequest {
    @NotNull(message = "Valor de compra es requerido")
    private BigDecimal purchaseValue;

    @NotEmpty(message = "Tipo de tasa es requerido")
    private String interestType;

    @NotEmpty(message = "Capitalizacion de tasa es requerido")
    private String capitalizationPeriod;

    @NotNull(message = "Tiempo de tasa es requerido")
    private Integer interestPeriod;

    @NotNull(message = "Valor de tasa es requerido")
    private BigDecimal interestRate;

    @NotEmpty(message = "Tipo de credito es requerido")
    private String creditType;

    @NotNull(message = "Numero de cuotas es requerido")
    private Integer installmentCount;

    @NotNull(message = "Plazo de gracia es requerido")
    private Boolean gracePeriod;

    @NotNull(message = "Periodo de gracia es requerido")
    private Integer gracePeriodLength;

    @NotNull(message = "ID del cliente es requerido")
    private UUID clientId;
}
