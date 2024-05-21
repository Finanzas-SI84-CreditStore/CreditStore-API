package com.creditstore.CreditStore.shared.email.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmailReq {

  private String[] recipients;

  private String subject;

  private String message;

}
