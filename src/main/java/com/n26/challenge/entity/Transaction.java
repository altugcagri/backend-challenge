package com.n26.challenge.entity;

import com.n26.challenge.validation.annotation.LastTimeInterval;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction implements Comparable<Transaction> {

  private static final int ONE_MINUTE = 60000;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private Double amount;

  @NotNull
  @LastTimeInterval(value = ONE_MINUTE)
  private Long timestamp;

  public Transaction() {
  }

  public Transaction(@NotNull Double amount, @NotNull @LastTimeInterval(value = 60000) Long timestamp) {
    this.amount = amount;
    this.timestamp = timestamp;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }


  @Override
  public int compareTo(Transaction transaction) {
    return timestamp.compareTo(transaction.getTimestamp());
  }
}
