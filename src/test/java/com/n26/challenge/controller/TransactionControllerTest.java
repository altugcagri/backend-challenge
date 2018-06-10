package com.n26.challenge.controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.n26.challenge.entity.Transaction;
import com.n26.challenge.exception.LastTimeIntervalException;
import com.n26.challenge.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

  @Mock
  private TransactionService transactionService;

  @InjectMocks
  private TransactionController sut;

  @Test
  public void testCreateTransaction_Success() throws LastTimeIntervalException {

    Transaction transaction = mock(Transaction.class);

    ResponseEntity responseEntity = sut.createTransaction(transaction);

    assertTrue(responseEntity.getStatusCode() == CREATED);
    verify(transactionService, Mockito.times(1)).createTransaction(transaction);
  }

  @Test
  public void testCreateTransaction_NotLastOneMinute() throws LastTimeIntervalException {

    Transaction transaction = mock(Transaction.class);
    doThrow(LastTimeIntervalException.class).when(transactionService).createTransaction(transaction);

    ResponseEntity responseEntity = sut.createTransaction(transaction);

    assertTrue(responseEntity.getStatusCode() == NO_CONTENT);
  }

  @Test
  public void testCreateTransaction_GeneraException() throws LastTimeIntervalException {

    Transaction transaction = mock(Transaction.class);
    doThrow(Exception.class).when(transactionService).createTransaction(transaction);

    ResponseEntity responseEntity = sut.createTransaction(transaction);

    assertTrue(responseEntity.getStatusCode() == BAD_REQUEST);
  }

}
