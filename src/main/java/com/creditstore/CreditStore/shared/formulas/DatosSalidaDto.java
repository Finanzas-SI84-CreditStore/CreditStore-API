package com.creditstore.CreditStore.shared.formulas;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatosSalidaDto {
    private Integer id;
    private double mes;
    private double saldoInicial;
    private double intereses;
    private double amortizacion;
    private double cuota;
    private double saldoFinal;
    private double flujo;
    private double tem;
    private String tipoPeriodoGracia;
    private double interesMoratorioPorCuota;
    private String estado;
    private String fecha;
    private Integer accountId;
}