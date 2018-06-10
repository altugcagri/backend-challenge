package com.n26.challenge.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.n26.challenge.entity.Transaction;
import com.n26.challenge.exception.LastTimeIntervalException;
import com.n26.challenge.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @RequestMapping(value = "/transactions", method = POST)
  public ResponseEntity<Void> createTransaction(@RequestBody Transaction transaction) {
    try {
      transactionService.createTransaction(transaction);
      return new ResponseEntity<>(CREATED);
    } catch (LastTimeIntervalException e) {
      return new ResponseEntity<>(NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(BAD_REQUEST);
    }
  }

}
