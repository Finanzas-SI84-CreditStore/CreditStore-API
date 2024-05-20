package com.creditstore.CreditStore.util.exception;

import lombok.Getter;
import lombok.ToString;
import com.creditstore.CreditStore.util.util.Error;
@Getter
@ToString
public class ServiceException extends RuntimeException{

  private Error error;

  public ServiceException(Error error){
    super(error.getMessage());
    this.error = error;
  }
}
