package com.n26.challenge.service;

import com.n26.challenge.entity.Transaction;
import com.n26.challenge.exception.LastTimeIntervalException;

public interface TransactionService {

  void createTransaction(Transaction transaction) throws LastTimeIntervalException;

}
