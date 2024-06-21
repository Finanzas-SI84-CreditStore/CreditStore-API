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

  PURCHASE_VALUE_REQUIRED(1017, "Valor de compra es requerido"),

  INTEREST_TYPE_REQUIRED(1018, "Tipo de tasa es requerido"),

  CAPITALIZATION_PERIOD_REQUIRED(1019, "Capitaizacion de tasa es requerido"),

  INTEREST_PERIOD_REQUIRED(1020, "Tiempo de tasa es requerido"),

  INTEREST_RATE_REQUIRED(1021, "Valor de tasa es requerido"),

  CREDIT_TYPE_REQUIRED(1022, "Tipo de credito es requerido"),

  INSTALLMENT_COUNT_REQUIRED(1023, "Numero de cuotas es requerido"),

  GRACE_PERIOD_REQUIRED(1024, "Plazo de gracia es requerido"),

  GRACE_PERIOD_LENGTH_REQUIRED(1025, "Periodo de gracia es requerido"),

  PAY_NOT_FOUND(1026, "No se encontró el pago"),
  PAYMENT_DATE_REQUIRED(1029, "Fecha de pago es requerida"), // Añadido

  CREDIT_LINE_EXCEEDED(1027, "El monto excede la línea de crédito"),


    ACCOUNT_NOT_FOUND(1028, "No se encontró la cuenta");



  private final int codError;
  private final String message;

  Error(int codError, String message){
    this.codError = codError;
    this.message = message;
  }
}
