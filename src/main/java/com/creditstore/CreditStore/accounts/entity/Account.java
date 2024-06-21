package com.creditstore.CreditStore.accounts.entity;

import com.creditstore.CreditStore.clients.entity.Client;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private BigDecimal ValorCompra;

    @NotNull
    private String TipoTasa;

    @NotNull
    private String CapitalizacionTasa;

    @NotNull
    private BigDecimal ValorTasa;

    @NotNull
    private String TipoCredito;

    @NotNull
    private Integer NumeroCuotas;

    @NotNull
    private Boolean PlazoGracia;

    @NotNull
    private Integer PeriodoGracia;

    @NotNull
    private LocalDate paymentDate;


    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
