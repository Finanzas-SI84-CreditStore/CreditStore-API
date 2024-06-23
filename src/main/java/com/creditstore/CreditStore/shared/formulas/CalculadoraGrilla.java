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
        if (datosEntrada.getTipoTasa().equals("EFECTIVA") && datosEntrada.getTiempoTasa() == 30) {
            TEM = datosEntrada.getTasa() / 100.00;
        } else {
            TEM = datosEntrada.calcularTEM(datosEntrada);
        }

        double saldoInicial = prestamo;
        double interes;
        double amortizacion = 0;
        double cuota = 0;
        double saldoFinal = prestamo;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datosEntrada.getFechaInicial());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");

        DatosSalida datosSalidaMes0 = new DatosSalida();
        datosSalidaMes0.setMes(0);
        datosSalidaMes0.setSaldoInicial(prestamo);
        datosSalidaMes0.setIntereses(0);
        datosSalidaMes0.setAmortizacion(0);
        datosSalidaMes0.setCuota(0);
        datosSalidaMes0.setSaldoFinal(prestamo);
        datosSalidaMes0.setFlujo(prestamo);
        datosSalidaMes0.setFecha(calendar.getTime());
        datos.add(datosSalidaMes0);

        for (double mesfila = 1; mesfila <= datosEntrada.getNumeroCuotas(); mesfila++) {
            DatosSalida datosSalida = new DatosSalida();

            calendar.add(Calendar.MONTH, 1); // Agregar un mes para cada iteración

            if (datosEntrada.getDiasAtraso() == 0) {
                datosSalida.setInteresMora(0);
            } else {
                datosSalida.setInteresMora(prestamo * (Math.pow(1 + datosEntrada.getTasaMoratoria() / 100, datosEntrada.getDiasAtraso() / 30) - 1));
            }

            interes = saldoInicial * TEM; // de momento obviamos el negativo

            double mesinvisible = mesfila;

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

            datosSalida.setEstado("POR_PAGAR");
            datosSalida.setFecha(calendar.getTime());

            datos.add(datosSalida);
        }

        return datos;
    }
}
