package com.creditstore.CreditStore.shared.formulas;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DatosEntrada {

    private String tipoTasa;
    private String tiempoTasa;

    private double capitalizacion;

    private double tasa;


    private String tipoPeriodoGracia;
    private double periodoGraciaMeses;
    private double mesInicio;
    private double numeroCuotas;

    private double limiteCredito;

    private double cuotasPeriodoGracia;
    private double montoPrestamo;

    public double calcularTEA(DatosEntrada datosEntrada) {

        double TEA;

        if(!datosEntrada.tipoTasa.equals("TEA")){
            TEA = Math.pow((1+(datosEntrada.tasa/100)/(360.00/datosEntrada.capitalizacion)),(360.00/datosEntrada.capitalizacion))-1;
        } else TEA = datosEntrada.tasa / 100.00;

        return TEA;
    }

    public double calcularCuota(double tasaInteres, double numeroPeriodos, double valorPresente, double valorFuturo, boolean tipo) {
        if (tasaInteres == 0) return -(valorPresente + valorFuturo) / numeroPeriodos;
        double factorPresente = Math.pow(1 + tasaInteres, numeroPeriodos);
        return (tasaInteres * (valorPresente * factorPresente + valorFuturo)) / (tipo ? (factorPresente - 1) * (1 + tasaInteres) : (factorPresente - 1));
    }
}
