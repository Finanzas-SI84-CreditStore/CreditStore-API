package com.creditstore.CreditStore.security.model;

import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserReq {
  @NotEmpty(message = "El campo nombres es requerido")
  private String name;

  @NotEmpty(message = "El campo apellidos es requerido")
  private String lastName;

  @NotEmpty(message = "El campo DNI es requerido")
  private String dni;

  @NotEmpty(message = "El campo correo es requerido")
  private String email;

  @NotEmpty(message = "El campo contraseña es requerido")
  private String password;

  @NotEmpty(message = "El campo fecha de nacimiento es requerido")
  private LocalDate birthDate;

  private String storeName;

}
