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
    private BigDecimal purchaseValue;

    @NotNull
    private String interestType;

    @NotNull
    private String capitalizationPeriod;

    @NotNull
    private Integer interestPeriod;

    @NotNull
    private BigDecimal interestRate;

    @NotNull
    private String creditType;

    @NotNull
    private Integer installmentCount;

    @NotNull
    private Boolean gracePeriod;

    @NotNull
    private Integer gracePeriodLength;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
