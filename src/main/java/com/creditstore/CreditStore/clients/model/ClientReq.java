package com.creditstore.CreditStore.clients.model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClientReq {

  @NotEmpty(message = "El campo nombre es requerido" )
  private String name;

  @NotEmpty(message = "El campo apellido es requerido")
  private String lastName;

  @NotEmpty(message = "El campo DNI es requerido")
  private String dni;

  @NotEmpty(message = "El campo fecha de nacimiento es requerido")
  private LocalDate birthDate;

  private String address;

  @NotEmpty(message = "El campo línea de crédito es requerido")
  private double creditLine;

  @AssertTrue(message = "El cliente debe ser mayor de edad")
  private boolean isBirthDateValid() {
    if (birthDate == null) {
      return false;
    }
    LocalDate now = LocalDate.now();
    LocalDate eighteenYearsAgo = now.minusYears(18);
    return birthDate.isBefore(eighteenYearsAgo) || birthDate.isEqual(eighteenYearsAgo);
  }


}
