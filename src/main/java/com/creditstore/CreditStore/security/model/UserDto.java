package com.creditstore.CreditStore.security.model;

import java.time.LocalDate;
import java.util.Date;

public interface UserDto {

  String getId();

  String getNames();

  String getLastNames();

  String getEmail();

  int getStatus();

  LocalDate getBirthDate();

  String getDni();

}
