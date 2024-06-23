package com.creditstore.CreditStore.util.formulas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DatosEntreda {
    private String moneda;
    private double precioVentaActivo;
    private double tipoPlan;
    private double cuotaInicialPorcentaje;
    private double cuotaFinalPorcentaje;
    private double capitalizacion;
    private double portes;
    private double tasaDescuentoPorcentaje;
    private double gastosAdmin;
    private double seguroDesgravamenPorcentaje;
    private double seguroRiesgoPorcentaje;
    private String tipoPeriodoGracia;
    private int periodoGraciaMeses;
    private double cuotasPeriodoGracia;
    private double tasa;
    private String tipoTasa;
    private double numeroAnios;
    private double tiempoDias;
    private double costesNotariales;
    private double costesRegistrales;
    private double GPS;
    private double freqPago;

    public double calcularTEA(DatosEntreda datosEntreda) {
        double TEA;

        if (!datosEntreda.getTipoTasa().equals("EFECTIVA")) {
            TEA = Math.pow((1 + (datosEntreda.getTasa() / 100) / (360.00 / datosEntreda.getCapitalizacion())), (360.00 / datosEntreda.getCapitalizacion())) - 1;
        } else TEA = datosEntreda.getTasa() / 100.00;

        return TEA;
    }
}
