package com.creditstore.CreditStore.shared.formulas;

import lombok.*;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DatosEntrada {


    private String tipoTasa; //tipotasa
    //MENSUAL = 30, TRIMESTRAL = 90, SEMESTRAL = 180, ANUAL = 360, DIARIA = 1, QUINCENAL = 15
    private double tiempoTasa;//tiempoTasa
    //MENSUAL = 30, TRIMESTRAL = 90, SEMESTRAL = 180, ANUAL = 360, DIARIA = 1, QUINCENAL = 15

    private double capitalizacion; //capitalizacionTasa
    private double tasa; //valortasa
    private String tipoPeriodoGracia; //plazogracia
    private double periodoGraciaMeses;
    private double numeroCuotas; //numerocuotas
    private double limiteCredito;
    private String tipoCredito; //tipocredito
    private double montoPrestamo; //valorcompra
    private double diasAtraso;
    private double tasaMoratoria;
    private Date fechaInicial;  // Nuevo atributo para la fecha inicial

    // ENTRA NOMINAL SALE EFECTIVA
    public double calcularTNaTEM(DatosEntrada datosEntrada) {
        double TEM;
        TEM = Math.pow((1 + ((datosEntrada.tasa / 100) / (datosEntrada.tiempoTasa / datosEntrada.capitalizacion))), (30 / datosEntrada.capitalizacion)) - 1;
        return TEM;
    }

    // ENTRA EFECTIVA SALE EFECTIVA
    public double calcularTEaTEM(DatosEntrada datosEntrada) {
        double TEM;
        TEM = Math.pow((1 + (datosEntrada.tasa / 100)), (30 / datosEntrada.tiempoTasa)) - 1;
        return TEM;
    }

    public double calcularCuota(double tasaInteres, double numeroPeriodos, double valorPresente, double valorFuturo, boolean tipo) {
        if (tasaInteres == 0) return -(valorPresente + valorFuturo) / numeroPeriodos;
        double factorPresente = Math.pow(1 + tasaInteres, numeroPeriodos);
        return (tasaInteres * (valorPresente * factorPresente + valorFuturo)) / (tipo ? (factorPresente - 1) * (1 + tasaInteres) : (factorPresente - 1));
    }
}
