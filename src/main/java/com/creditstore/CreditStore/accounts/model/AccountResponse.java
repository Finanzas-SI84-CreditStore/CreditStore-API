package com.creditstore.CreditStore.accounts.model;

import com.creditstore.CreditStore.clients.entity.Client;
import com.creditstore.CreditStore.shared.formulas.DatosEntrada;
import com.creditstore.CreditStore.shared.formulas.DatosSalida;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class AccountResponse {

    private DatosEntrada datosEntrada;
    private List<DatosSalida> datosSalidaList;
}
