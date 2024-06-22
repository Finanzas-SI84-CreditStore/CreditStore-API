package com.creditstore.CreditStore.shared.formulas;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CalculadoraGrilla {

    public static List<DatosSalida> calculadora(DatosEntrada datosEntrada) {

        if ("VENCIMIENTO".equalsIgnoreCase(datosEntrada.getTipoCredito())) {
            datosEntrada.setNumeroCuotas(1);
        }

        List<DatosSalida> datos = new ArrayList<>();

        double prestamo = datosEntrada.getMontoPrestamo();
        double flujo = 0;
        double TEM = 0;
        if (datosEntrada.getTipoTasa().equals("TEA") && datosEntrada.getTiempoTasa() == 30) {
            TEM = datosEntrada.getTasa() / 100.00;
        } else {
            TEM = datosEntrada.calcularTEM(datosEntrada);
        }

        double saldoInicial = 0;
        double interes;
        double amortizacion = 0;
        double cuota = 0;
        double saldoFinal = 0;

        double mesInicio = 1;
        double mesfila = 0;

        // Inicializar la fecha con la fecha proporcionada por el usuario
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datosEntrada.getFechaInicial());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");

        for (double mesinvisible = 0; mesinvisible <= datosEntrada.getNumeroCuotas(); mesinvisible++) {
            if (mesfila < mesInicio) {
                saldoInicial = 0;
            } else if (mesfila == mesInicio) {
                saldoInicial = prestamo;
            } else if (mesfila <= datosEntrada.getPeriodoGraciaMeses() + mesInicio - 1) {
                saldoInicial = saldoFinal + (mesfila == mesInicio ? prestamo : 0);
            } else {
                saldoInicial = saldoFinal;
            }
            DatosSalida datosSalida = new DatosSalida();

            // Establecer la fecha para cada mes
            if (mesfila == 0) {
                datosSalida.setSaldoInicial(prestamo);
                datosSalida.setIntereses(0);
                datosSalida.setAmortizacion(0);
                datosSalida.setCuota(0);
                datosSalida.setSaldoFinal(prestamo);
                datosSalida.setFlujo(prestamo);
                datosSalida.setFecha(calendar.getTime());
            } else {
                calendar.add(Calendar.MONTH, 1); // Agregar un mes para cada iteración

                if (datosEntrada.getDiasAtraso() == 0) {
                    datosSalida.setInteresMora(0);
                } else {
                    datosSalida.setInteresMora(prestamo * (Math.pow(1 + datosEntrada.getTasaMoratoria() / 100, datosEntrada.getDiasAtraso() / 30) - 1));
                }

                interes = saldoInicial * TEM; // de momento obviamos el negativo

                mesinvisible = mesfila - mesInicio + 1;

                if (mesinvisible <= datosEntrada.getPeriodoGraciaMeses() && datosEntrada.getTipoPeriodoGracia().equals("T")) {
                    cuota = 0;
                } else if (mesinvisible <= datosEntrada.getPeriodoGraciaMeses() && datosEntrada.getTipoPeriodoGracia().equals("P")) {
                    cuota = interes;
                } else if (mesinvisible <= datosEntrada.getNumeroCuotas()) {
                    double numeroPeriodos = datosEntrada.getNumeroCuotas() - mesinvisible + 1;
                    if (mesinvisible > 0) {
                        cuota = datosEntrada.calcularCuota(TEM, numeroPeriodos, saldoInicial, 0, false);
                    }
                }

                amortizacion = cuota - interes;
                saldoFinal = saldoInicial - amortizacion;
                if (saldoFinal < 0.0000001) {
                    saldoFinal = 0;
                }
                flujo = cuota;

                datosSalida.setMes(mesinvisible);
                datosSalida.setSaldoInicial(saldoInicial);
                datosSalida.setIntereses(interes);
                if (amortizacion < 0.0000001) {
                    amortizacion = 0;
                }
                datosSalida.setAmortizacion(amortizacion);
                datosSalida.setCuota(cuota);
                datosSalida.setSaldoFinal(saldoFinal);
                datosSalida.setFlujo(flujo);
                datosSalida.setTem(TEM);
                if (mesinvisible <= datosEntrada.getPeriodoGraciaMeses()) {
                    datosSalida.setTipoPeriodoGracia(datosEntrada.getTipoPeriodoGracia());
                } else {
                    datosSalida.setTipoPeriodoGracia("S");
                }

                saldoInicial = saldoFinal;

                // Establecer la fecha calculada
                datosSalida.setFecha(calendar.getTime());
            }

            if (mesinvisible > 0) {
                datos.add(datosSalida);
            }

            mesfila++;
        }

        return datos;
    }
}
