package com.cyacompany.projectmanagement_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BusinessLogicException extends RuntimeException {
  public BusinessLogicException(String message) {
    super(message);
  }
}
