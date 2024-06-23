package com.creditstore.CreditStore.clients.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ClientDto {

  private final UUID id;

  private final String name;

  private final String lastName;

  private final String dni;

  private final LocalDate birthDate;

  private final String address;

  private final double creditLine;

  private double debt;

  private double availableBalance;


}
