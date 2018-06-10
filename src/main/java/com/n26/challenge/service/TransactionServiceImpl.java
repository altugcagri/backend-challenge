package com.n26.challenge.service;

import com.n26.challenge.entity.Transaction;
import com.n26.challenge.exception.LastTimeIntervalException;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired
  private StatisticsService statisticsService;

  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  @Override
  public void createTransaction(Transaction transaction) throws LastTimeIntervalException {

    final Set<ConstraintViolation<Transaction>> violations = validator.validate(transaction);

    if (!violations.isEmpty()) {
      throw new LastTimeIntervalException("Transaction is older than last 1 minute");
    }

    statisticsService.addTransaction(transaction);

  }
}
