package com.creditstore.CreditStore.util.util;

import lombok.Getter;

@Getter
public enum Error {
  GENERIC_ERROR(1001, "Se presentó un problema, reporte e intente luego"),

  USER_NOT_FOUND(1002, "No se encontró el usuario"),

  USER_NOT_EXISTS(1003, "El usuario no existe"),

  EMAIL_ALREADY_EXIST(1004, "El correo ya se encuentra registrado"),

  INVALID_PASSWORD(1005, "La contraseña debe tener al menos 8 caracteres: mínimo un número,"
      + " una minúscula y una mayúscula"),
  INVALID_USER_DATA(1006, "Datos del usuario inválidos"),

  INVALID_EMAIL(1007, "Ingrese un correo válido"),

  INVALID_OTP(1008, "Otp inválido"),

  INVALID_LOGIN(1009, "Email o contraseña inválidas"),

  EXIST_CLIENT(1010, "El DNI ya se encuentra registrado"),

  CLIENT_NOT_FOUND(1011, "No se encontró el cliente"),

  CLIENT_NOT_EXISTS(1012, "El cliente no existe"),

  USER_ALREADY_EXIST(1013, "El usuario ya se encuentra registrado"),

  INVALID_BIRTH_DATE(1014, "El usuario debe ser mayor de edad"),

  INVALID_DNI(1015, "El DNI debe tener 8 dígitos"),

  INVALID_PAYMENT_DAY(1016, "El día de pago debe ser 5, 10, 15, 20 o 25"),
  ;

  private final int codError;

  private final String message;
  Error(int codError, String message){
    this.codError = codError;
    this.message = message;
  }

}
