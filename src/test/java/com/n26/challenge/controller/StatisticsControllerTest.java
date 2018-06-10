package com.n26.challenge.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import com.n26.challenge.entity.Statistics;
import com.n26.challenge.service.StatisticsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsControllerTest {

  @Mock
  StatisticsService statisticsService;

  @InjectMocks
  StatisticsController sut;

  @Test
  public void testGetStatistics(){

    ResponseEntity<Statistics> statistics = sut.getStatistics();

    assertNotNull(statistics);
    verify(statisticsService, Mockito.times(1)).getStatistics();
  }

}
