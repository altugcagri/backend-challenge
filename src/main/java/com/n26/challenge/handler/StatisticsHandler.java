package com.n26.challenge.handler;

import static java.lang.System.currentTimeMillis;
import static java.util.stream.Collectors.toList;

import com.n26.challenge.entity.Statistics;
import com.n26.challenge.entity.Transaction;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StatisticsHandler {

  private static final long ONE_SEC = 1000;
  private static final int ONE_MINUTE = 60000;

  private final PriorityBlockingQueue<Transaction> transactions = new PriorityBlockingQueue<>();
  private final Statistics statistics = new Statistics(0D, 0D, 0D, 0D, 0L);
  private final Object obj = new Object();

  public void saveTransaction(Transaction transaction) {
    transactions.add(transaction);
    updateStatistics();
  }

  public Statistics getLatestStatistics() {
    synchronized (obj) {
      return statistics;
    }
  }

  @Scheduled(fixedRate = ONE_SEC)
  private void processOlderTransactions() {
    while (!transactions.isEmpty()) {
      Transaction transaction = transactions.peek();
      if (currentTimeMillis() - transaction.getTimestamp() > ONE_MINUTE) {
        transactions.poll();
        updateStatistics();
      }
    }
  }

  private void updateStatistics() {
    final List<Double> amountList = transactions.stream().map(Transaction::getAmount).collect(toList());
    final Long count = amountList.stream().count();
    Double sum = 0D;
    Double avg = 0D;
    Double max = 0D;
    Double min = 0D;
    if (count > 0) {
      sum = amountList.stream().mapToDouble(Double::doubleValue).sum();
      avg = amountList.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
      max = amountList.stream().max(Double::compareTo).get();
      min = amountList.stream().min(Double::compareTo).get();
    }
    synchronized (obj) {
      statistics.setCount(count);
      statistics.setSum(sum);
      statistics.setAvg(avg);
      statistics.setMax(max);
      statistics.setMin(min);
    }

  }

}
