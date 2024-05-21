package com.creditstore.CreditStore.shared.email.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordReq {

  @NotEmpty(message = "El campo contraseña es requerido")
  private String password;

  @NotEmpty(message = "El campo nueva contraseña es requerido")
  private String newPassword;

}
