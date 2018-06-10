package com.n26.challenge.service;

import static java.lang.System.currentTimeMillis;
import static org.junit.Assert.assertTrue;

import com.n26.challenge.TestConfig;
import com.n26.challenge.entity.Transaction;
import com.n26.challenge.exception.LastTimeIntervalException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class TransactionServiceTest {

  @Autowired
  private TransactionService sut;

  @Test
  public void testCreateTransaction_Success() {

    Transaction transaction = new Transaction(12.3, currentTimeMillis());
    try {
      sut.createTransaction(transaction);
      assertTrue(true);
    } catch (LastTimeIntervalException e) {
      assertTrue(false);
    }

  }


  @Test(expected = LastTimeIntervalException.class)
  public void testCreateTransaction_Fail() throws LastTimeIntervalException {

    Transaction transaction = new Transaction(12.3, currentTimeMillis() - 700000L);

    sut.createTransaction(transaction);


  }

}
