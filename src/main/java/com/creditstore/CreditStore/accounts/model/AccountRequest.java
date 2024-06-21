package com.creditstore.CreditStore.accounts.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class AccountRequest {
    @NotNull(message = "Valor de compra es requerido")
    private BigDecimal valorCompra;

    @NotEmpty(message = "Tipo de tasa es requerido")
    private String tipoTasa;

    @NotEmpty(message = "Capitalizacion de tasa es requerido")
    private String capitalizacionTasa;

    @NotNull(message = "Valor de tasa es requerido")
    private BigDecimal valorTasa;

    @NotEmpty(message = "Tipo de credito es requerido")
    private String tipoCredito;

    @NotNull(message = "Numero de cuotas es requerido")
    private Integer numeroCuotas;

    @NotNull(message = "Plazo de gracia es requerido")
    private Boolean plazoGracia;

    @NotNull(message = "Periodo de gracia es requerido")
    private Integer periodoGracia;

    @NotNull(message = "Fecha de pago es requerido")
    private LocalDate paymentDate; // Añadido

    @NotNull(message = "ID del cliente es requerido")
    private UUID clientId;
}
