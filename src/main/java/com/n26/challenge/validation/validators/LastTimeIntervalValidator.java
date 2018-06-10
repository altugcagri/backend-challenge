package com.n26.challenge.validation.validators;

import static java.lang.System.currentTimeMillis;

import com.n26.challenge.validation.annotation.LastTimeInterval;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LastTimeIntervalValidator implements ConstraintValidator<LastTimeInterval, Long> {

  private long timeInterval;

  @Override
  public void initialize(LastTimeInterval lastTimeInterval) {
    this.timeInterval = lastTimeInterval.value();

  }

  @Override
  public boolean isValid(Long timestampValue, ConstraintValidatorContext context) {

    return (currentTimeMillis() >= timestampValue) && (timestampValue >= currentTimeMillis() - timeInterval);
  }

}
