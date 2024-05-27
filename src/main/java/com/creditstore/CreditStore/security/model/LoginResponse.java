package com.creditstore.CreditStore.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private String message;
}
