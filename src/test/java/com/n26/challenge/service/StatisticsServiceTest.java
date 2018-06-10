package com.n26.challenge.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.n26.challenge.entity.Statistics;
import com.n26.challenge.entity.Transaction;
import com.n26.challenge.handler.StatisticsHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {

  @Mock
  private StatisticsHandler statisticsHandler;

  @InjectMocks
  private StatisticsService sut = new StatisticsServiceImpl();

  @Test
  public void testGetStatistics() {

    Statistics mockStatistics = mock(Statistics.class);
    when(statisticsHandler.getLatestStatistics()).thenReturn(mockStatistics);

    Statistics statistics = sut.getStatistics();

    assertEquals(statistics, mockStatistics);
  }

  @Test
  public void test() {

    Transaction transaction = mock(Transaction.class);

    sut.addTransaction(transaction);

    verify(statisticsHandler, Mockito.times(1)).saveTransaction(transaction);


  }
}
