package com.n26.challenge.exception;

public class LastTimeIntervalException extends Exception {


  public LastTimeIntervalException(String message) {
    super(message);
  }

  public LastTimeIntervalException(String message, Exception e) {
    super(message, e);
  }

  public LastTimeIntervalException(Throwable t) {
    super(t);
  }

}
