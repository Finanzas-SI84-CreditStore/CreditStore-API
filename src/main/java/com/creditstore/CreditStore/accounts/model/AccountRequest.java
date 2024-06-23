package com.creditstore.CreditStore.accounts.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class AccountRequest {
    @NotNull(message = "Valor de compra es requerido")
    private Double valorCompra;//montoPrestamo

    @NotEmpty(message = "Tipo de tasa es requerido")
    private String tipoTasa;//tipoTasa

    @NotEmpty(message = "Capitalizacion de tasa es requerido")
    private Double capitalizacionTasa;//capitalizacion

    @NotNull(message = "Valor de tasa es requerido")
    private Double valorTasa;//tasa

    @NotEmpty(message = "Tipo de credito es requerido")
    private String tipoCredito;//NOENTRADA - A vencimiento o cuotas

    @NotNull(message = "Numero de cuotas es requerido")
    private Double numeroCuotas;//numeroCuotas

    @NotNull(message = "Plazo de gracia es requerido")
    private String plazoGracia;//tipoPeriodoGracia - TOTAL, NO, PARCIAL

    @NotNull(message = "Periodo de gracia es requerido")
    private Double periodoGracia;//periodoGraciaMeses

    @NotNull(message = "Fecha de pago es requerido")
    private Date paymentDate; //fechaInicial

    private Double tasaMoratoria;//tasaMoratoria

    private Double diasAtraso;//diasAtraso

    private Double limiteCredito;//limiteCredito

    private Double tiempoTasa;//tiempoTasa

    @NotNull(message = "ID del cliente es requerido")
    private UUID clientId;
}
