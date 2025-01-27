package com.bccalculator.bc_calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bccalculator.bc_calculator.model.BcCalculator;

public interface BcCalculatorOperation {
  
  @GetMapping(value = "/operation")
  public BcCalculator calculateByRequestParam(@RequestParam String x, @RequestParam String y, @RequestParam String operation);

  @PostMapping(value = "/operation")
  public BcCalculator calculateByRequestBody(@RequestBody BcCalculator requestCal);

  @GetMapping(value = "/operation/{x}/{y}/{operation}")
  public BcCalculator calculateByPathVariable(@PathVariable String x, @PathVariable String y, @PathVariable String operation);

}
