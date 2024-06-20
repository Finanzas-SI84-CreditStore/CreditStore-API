package com.creditstore.CreditStore.accounts.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountResponse {
    private Integer id;
    private BigDecimal ValorCompra;
    private String TipoTasa;
    private String CapitalizacionTasa;
    private BigDecimal ValorTasa;
    private String TipoCredito;
    private Integer NumeroCuotas;
    private Boolean PlazoGracia;
    private Integer PeriodoGracia;
}
