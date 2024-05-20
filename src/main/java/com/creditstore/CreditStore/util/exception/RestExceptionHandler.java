package com.creditstore.CreditStore.util.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.creditstore.CreditStore.util.model.*;

@ControllerAdvice
public class RestExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(InvalidDataException exc){
    logger.error(exc.toString(), exc);
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    ServiceException sexc = new ServiceException(exc.getError());

    List<String> errors = exc.getResult()
        .getAllErrors()
        .stream()
        .map(fieldError -> fieldError.getDefaultMessage())
        .collect(Collectors.toList());
    return buildResponseEntity(sexc, httpStatus, errors);
  }

  @ExceptionHandler
  protected ResponseEntity<ErrorResponse> handleException(ServiceException exc) {
    logger.error(exc.toString(), exc);
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    return buildResponseEntity(exc, httpStatus, null);
  }

  private ResponseEntity<ErrorResponse> buildResponseEntity(ServiceException exc,
      HttpStatus httpStatus, List<String> errorDetails) {
    ErrorResponse errorResponse = ErrorResponse.builder()
        .codError(exc.getError().getCodError())
        .message(exc.getError().getMessage())
        .timestamp(new Date())
        .details(errorDetails)
        .build();
    return new ResponseEntity<>(errorResponse, httpStatus);
  }

}
