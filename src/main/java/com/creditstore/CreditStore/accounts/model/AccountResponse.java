package com.creditstore.CreditStore.accounts.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class AccountResponse {
    private Integer id;
    private BigDecimal valorCompra;
    private String tipoTasa;
    private String capitalizacionTasa;
    private BigDecimal valorTasa;
    private String tipoCredito;
    private Integer numeroCuotas;
    private Boolean plazoGracia;
    private Integer periodoGracia;
    private LocalDate paymentDate; // Añadido
}
