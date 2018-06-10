package com.n26.challenge.controller;

import static org.springframework.http.HttpStatus.OK;

import com.n26.challenge.entity.Statistics;
import com.n26.challenge.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StatisticsController {

  @Autowired
  private StatisticsService statisticsService;

  @RequestMapping("/statistics")
  @ResponseBody
  public ResponseEntity<Statistics> getStatistics() {
    return new ResponseEntity<>(statisticsService.getStatistics(), OK);
  }
}
