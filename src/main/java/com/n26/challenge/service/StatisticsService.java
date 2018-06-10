package com.n26.challenge.service;

import com.n26.challenge.entity.Statistics;
import com.n26.challenge.entity.Transaction;

public interface StatisticsService {

  void addTransaction (Transaction transaction);

  Statistics getStatistics();

}
