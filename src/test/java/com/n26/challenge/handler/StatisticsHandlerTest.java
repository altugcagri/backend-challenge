package com.n26.challenge.handler;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.sleep;
import static junit.framework.TestCase.assertTrue;

import com.n26.challenge.TestConfig;
import com.n26.challenge.entity.Statistics;
import com.n26.challenge.entity.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class StatisticsHandlerTest {

  @Autowired
  private StatisticsHandler sut;


  @Test
  public void test_StatisticsHandler() throws InterruptedException {

    sut.saveTransaction(createTransaction(15D, 70000L));
    sut.saveTransaction(createTransaction(11D, 10000L));
    sut.saveTransaction(createTransaction(12D, 10000L));
    sut.saveTransaction(createTransaction(13D, 10000L));
    sut.saveTransaction(createTransaction(14D, 10000L));
    sut.saveTransaction(createTransaction(15D, 10000L));

    sleep(2000L);

    Statistics statistics = sut.getLatestStatistics();

    assertTrue(statistics.getCount() == 5);
    assertTrue(statistics.getAvg() == 13D);
    assertTrue(statistics.getSum() == 65D);
    assertTrue(statistics.getMax() == 15D);
    assertTrue(statistics.getMin() == 11D);

  }


  private Transaction createTransaction(Double amount, Long timeInterval) {
    return new Transaction(amount, currentTimeMillis() - timeInterval);
  }

}
