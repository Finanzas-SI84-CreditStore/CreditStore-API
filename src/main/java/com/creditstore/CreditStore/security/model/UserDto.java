package com.creditstore.CreditStore.security.model;

import java.util.Date;

public interface UserDto {

  String getId();

  String getNames();

  String getLastNames();

  String getEmail();

  int getStatus();

  Date getBirthDate();

  String getDni();

}
