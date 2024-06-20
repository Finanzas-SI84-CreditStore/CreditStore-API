package com.creditstore.CreditStore.shared.formulas;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DatosSalida {
    private double mes;
    private double saldoInicial;
    private double intereses;
    private double amortizacion;
    private double cuota;
    private double saldoFinal;
    private double flujo;
    private double tem;
}
