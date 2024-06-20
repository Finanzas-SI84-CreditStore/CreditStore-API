package com.creditstore.CreditStore.shared.formulas;


import java.util.ArrayList;
import java.util.List;

public class CalculadoraGrilla {

    public static List<DatosSalida> calculadora(DatosEntrada datosEntrada) {
        List<DatosSalida> datos = new ArrayList<>();



        //Validacion de Tasa
        double TEA = datosEntrada.calcularTEA(datosEntrada);

        double prestamo = datosEntrada.getMontoPrestamo();
        double flujo = 0;
        double TEM = 0;
        if (datosEntrada.getTiempoTasa().equals("MENSUAL")) {
            TEM = datosEntrada.getTasa() / 100.00;
        } else {
            TEM = Math.pow(1 + TEA, 1.0 / 12) - 1;
        }


        double saldoInicial = prestamo;
        double interes;
        double amortizacion = 0;
        double cuota = 0;
        double saldoFinal;

        for (double mes = 0; mes <= datosEntrada.getNumeroCuotas(); mes++) {
            DatosSalida datosSalida = new DatosSalida();
            datosSalida.setMes(mes);

            if (mes == 0) {
                datosSalida.setSaldoInicial(prestamo);
                datosSalida.setIntereses(0);
                datosSalida.setAmortizacion(0);
                datosSalida.setCuota(0);
                datosSalida.setSaldoFinal(prestamo);
                datosSalida.setFlujo(prestamo);
            } else {

                interes = saldoInicial * TEM; //de momento obviamos el negativo

                if (mes <= datosEntrada.getPeriodoGraciaMeses() && datosEntrada.getTipoPeriodoGracia().equals("T")) {
                    cuota = 0;
                } else if (mes <= datosEntrada.getPeriodoGraciaMeses() && datosEntrada.getTipoPeriodoGracia().equals("P")) {
                    cuota = interes;
                } else if (mes <= datosEntrada.getNumeroCuotas()) {
                    cuota = datosEntrada.calcularCuota(TEM, datosEntrada.getNumeroCuotas() - mes + 1, saldoInicial, 0, false);
                    amortizacion = cuota - interes;
                }


                saldoFinal = saldoInicial - amortizacion;

                if(saldoFinal < 0.0000001) {
                    saldoFinal = 0;
                }

                flujo = cuota;

                datosSalida.setSaldoInicial(saldoInicial);
                datosSalida.setIntereses(interes);
                datosSalida.setAmortizacion(amortizacion);
                datosSalida.setCuota(cuota);
                datosSalida.setSaldoFinal(saldoFinal);
                datosSalida.setFlujo(flujo);
                datosSalida.setTem(TEM);

                saldoInicial = saldoFinal;
            }

            datos.add(datosSalida);
        }

        return datos;

    }


}
