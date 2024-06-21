package com.creditstore.CreditStore.clients.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientQuery {
  private String name;

  private String lastName;

  private String dni;

  private LocalDate birthDate;

  private String address;

  private double creditLine;

  private double debt;

  private double availableBalance;
}
