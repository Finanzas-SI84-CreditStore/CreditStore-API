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
    private BigDecimal ValorCompra;

    @NotEmpty(message = "Tipo de tasa es requerido")
    private String TipoTasa;

    @NotEmpty(message = "Capitalizacion de tasa es requerido")
    private String CapitalizacionTasa;

    @NotNull(message = "Valor de tasa es requerido")
    private BigDecimal ValorTasa;

    @NotEmpty(message = "Tipo de credito es requerido")
    private String TipoCredito;

    @NotNull(message = "Numero de cuotas es requerido")
    private Integer NumeroCuotas;

    @NotNull(message = "Plazo de gracia es requerido")
    private Boolean PlazoGracia;

    @NotNull(message = "Periodo de gracia es requerido")
    private Integer PeriodoGracia;

    @NotNull(message = "ID del cliente es requerido")
    private UUID clientId;
}
