package com.creditstore.CreditStore.shared.formulas;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.creditstore.CreditStore.accounts.entity.Account;
import com.creditstore.CreditStore.clients.entity.Client;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DatosSalida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private double interesMora;
    private String estado;
    private String fecha;  // Nuevo atributo para la fecha
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // Método para establecer la fecha en formato dd/MM/yy
    public void setFecha(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        this.fecha = formatter.format(date);
    }
}
