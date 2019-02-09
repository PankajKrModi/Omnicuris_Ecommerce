package com.omnicuris.ecommerce.error;

import org.springframework.http.HttpStatus;

public class ServiceResponseException extends Exception {

  private HttpStatus status;
  private String message;

  public static ServiceResponseException status(HttpStatus status) {
    ServiceResponseException exception = new ServiceResponseException();
    exception.setStatus(status);
    return exception;
  }

  public static ServiceResponseException error(String message) {
    ServiceResponseException exception = new ServiceResponseException();
    exception.setMessage(message);
    return exception;
  }

  public ServiceResponseException message(String message) {
    this.setMessage(message);
    return this;
  }

public HttpStatus getStatus() {
	return status;
}

public void setStatus(HttpStatus status) {
	this.status = status;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}
  
  
}
