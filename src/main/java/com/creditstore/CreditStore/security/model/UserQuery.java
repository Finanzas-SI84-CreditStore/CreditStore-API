package com.creditstore.CreditStore.security.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {

  private UUID id;

  private String names;

  private String lastNames;

  private String dni;

  private String email;

  private int status;

}
