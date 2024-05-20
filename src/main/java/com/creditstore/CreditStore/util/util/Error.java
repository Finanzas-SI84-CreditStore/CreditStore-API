package com.creditstore.CreditStore.util.util;

import lombok.Getter;

@Getter
public enum Error {
  GENERIC_ERROR(1001, "Se presentó un problema, reporte e intente luego"),

  USER_NOT_FOUND(1002, "No se encontró el usuario"),

  USER_NOT_EXISTS(1003, "El usuario no existe"),

  USER_ALREADY_EXIST(1004, "El correo ya se encuentra registrado"),

  INVALID_PASSWORD(1005, "La contraseña debe tener al menos 8 caracteres: mínimo un número,"
      + " una minúscula y una mayúscula"),
  INVALID_USER_DATA(1006, "Datos del usuario inválidos"),
  ;

  private final int codError;

  private final String message;
  Error(int codError, String message){
    this.codError = codError;
    this.message = message;
  }

}
