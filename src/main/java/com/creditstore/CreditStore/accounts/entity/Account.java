package com.creditstore.CreditStore.accounts.entity;

import com.creditstore.CreditStore.clients.entity.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    private Double valorCompra;

    @NotNull
    private String tipoTasa;

    @NotNull
    private Double capitalizacionTasa;

    @NotNull
    private Double valorTasa;

    @NotNull
    private String tipoCredito;

    @NotNull
    private Double numeroCuotas;

    @NotNull
    private String plazoGracia;

    @NotNull
    private Double periodoGracia;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    private Double tasaMoratoria;

    private Double diasAtraso;

    private Double limiteCredito;

    private Double tiempoTasa;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}