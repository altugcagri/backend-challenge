package com.n26.challenge.service;

import com.n26.challenge.entity.Statistics;
import com.n26.challenge.entity.Transaction;
import com.n26.challenge.handler.StatisticsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

  @Autowired
  private StatisticsHandler statisticsHandler;

  @Override
  public void addTransaction(Transaction transaction) {
    statisticsHandler.saveTransaction(transaction);
  }

  @Override
  public Statistics getStatistics() {
    return statisticsHandler.getLatestStatistics();
  }
}
