package com.creditstore.CreditStore.util.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import com.creditstore.CreditStore.util.util.Error;
@Getter
public class InvalidDataException extends RuntimeException{

  private final transient BindingResult result;

  private Error error;

  public InvalidDataException(BindingResult result, Error error) {
    super(error.getMessage());
    this.result  = result;
    this.error = error;
  }
}
