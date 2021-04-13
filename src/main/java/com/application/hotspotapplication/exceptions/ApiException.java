package com.application.hotspotapplication.exceptions;

import java.util.Date;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiException {

  private final String message;
  private final HttpStatus httpStatus;
  private final Date timeStamp;

  public ApiException(String message, HttpStatus httpStatus, Date timeStamp) {
    this.message = message;
    this.httpStatus = httpStatus;
    this.timeStamp = timeStamp;
  }


}