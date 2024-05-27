package com.creditstore.CreditStore.security.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotEmpty(message = "El campo correo es requerido")
    private String email;

    @NotEmpty(message = "El campo contraseña es requerido")
    private String password;
}
