package com.creditstore.CreditStore.accounts.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class PayRequest {
    @NotEmpty(message = "El campo monto es requerido")
    private double amount;

    @NotEmpty(message = "El campo fecha de pago es requerido")
    private LocalDate date;


}
