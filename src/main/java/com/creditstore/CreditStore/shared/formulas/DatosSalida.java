package com.creditstore.CreditStore.shared.formulas;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private String tipoPeriodoGracia;
    private double interesMora;
    private String fecha;  // Nuevo atributo para la fecha

    // Método para establecer la fecha en formato dd/MM/yy
    public void setFecha(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        this.fecha = formatter.format(date);
    }
}
