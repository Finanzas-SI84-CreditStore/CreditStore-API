package com.creditstore.CreditStore.shared.formulas;

import lombok.*;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DatosEntrada {

    private String tipoTasa;//tipoTasa
    //MENSUAL = 30, TRIMESTRAL = 90, SEMESTRAL = 180, ANUAL = 360, DIARIA = 1, QUINCENAL = 15
    private double tiempoTasa;//tiempoTasa
    //MENSUAL = 30, TRIMESTRAL = 90, SEMESTRAL = 180, ANUAL = 360, DIARIA = 1, QUINCENAL = 15
    private double capitalizacion;//capitalizacionTasa
    private double tasa;//valorTasa
    private String tipoPeriodoGracia;//plazoGracia
    private double periodoGraciaMeses;//periodoGracia
    private double numeroCuotas;//numeroCuotas
    private double limiteCredito;//limiteCredito
    private double montoPrestamo; //ValorCompra
    private double diasAtraso;//diasAtraso
    private double tasaMoratoria;//tasaMoratoria
    private Date fechaInicial;  //paymentDate

    // ENTRA NOMINAL SALE EFECTIVA
    public double calcularTEM(DatosEntrada datosEntrada) {
        double TEM;
        TEM = Math.pow((1 + ((datosEntrada.tasa / 100) / (datosEntrada.tiempoTasa / datosEntrada.capitalizacion))), (30 / datosEntrada.capitalizacion)) - 1;
        return TEM;
    }

    public double calcularCuota(double tasaInteres, double numeroPeriodos, double valorPresente, double valorFuturo, boolean tipo) {
        if (tasaInteres == 0) return -(valorPresente + valorFuturo) / numeroPeriodos;
        double factorPresente = Math.pow(1 + tasaInteres, numeroPeriodos);
        return (tasaInteres * (valorPresente * factorPresente + valorFuturo)) / (tipo ? (factorPresente - 1) * (1 + tasaInteres) : (factorPresente - 1));
    }
}
